package com.liuyun.springcloudhystrix.hystrix;

import com.liuyun.common.entity.User;
import com.liuyun.springcloudhystrix.service.IUserService;
import com.netflix.hystrix.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserCollapseCommand extends HystrixCollapser<List<User>, User, Integer> {

    private IUserService userService;

    private Integer userId;

    public UserCollapseCommand(IUserService userService, Integer userId) {
        super(Setter.
                withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapsedCommand"))
                .andCollapserPropertiesDefaults(
                        HystrixCollapserProperties.Setter()
                .withTimerDelayInMilliseconds(100))
                //设置合并请求的作用域，
                // 默认情况是Scope.RUQUEST，这个代表对单线程内的一次或者多次调用进行合并，
                // 此处还需注意，histrix 认为在任何时刻同一线程对同一服务调用的时候，如果传递的参数内容一样，
                // hystrix认为，这几次的调用结果是一样的，因此，会对相同的参数去重，然后在做批量操作。
                // 例如：在某一线程A内，连接想service-1服务，发起5次查询user操作，如果五次的id分别是：
                // 100、100、200、300、400，则合并之后向service-1发起请求的时候携带的参数是100、200、300、400.
                // 但是对于我们额web环境而言，实际是一个多线程环境，因此大部分情况下需要将多个线程的请求进行合并。因此，
                // 将作用域设置为 GLOBAL。多线程情况下的调用，默认不会去重。
                .andScope(Scope.GLOBAL));
        this.userService = userService;
        this.userId = userId;
    }

    @Override
    public Integer getRequestArgument() {
        return userId;
    }

    /*
    这个方法中的collapsedRequest 对象保存了延时时间窗口中收集了所有获取当个User的请求，
    通过获取这些请求参数来构造我们准备的批量请求命令 UserBatchCommand
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User,
            Integer>> collapsedRequests) {
        List<Integer> userIds = new ArrayList<>(collapsedRequests.size());
        userIds.addAll(collapsedRequests.stream()
                .map(CollapsedRequest :: getArgument).collect(Collectors.toList()));

        return new UserBatchCommand(userService, userIds);
    }

    /*
        在批量请求命令UserBatchCommand实例被触发执行后，接着就执行该方法，
        其中users保存了之前createCommand构造的批量请求命令的结果。
        而collapsedRequests 中保存了被合并的所有请求，
        此处需要做的就是将每个请求的结果（User）保存到相应的 collapsedRequest
        此处，需要考虑两个集合元素个数不等或者顺序不相符的情况
        假如不相等，我们需要为每个collapsedRequest找到相应的User(如果找不到则以null替代)
     */

    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Integer>>
            collapsedRequests) {
        Map<Integer, User> userMap = users.stream().collect(
                Collectors.toMap(User :: getId,Function.identity()));
        for(CollapsedRequest<User, Integer> collapsedRequest : collapsedRequests){
            Integer userId = collapsedRequest.getArgument();
            collapsedRequest.setResponse(userMap.get(userId));
        }
    }
}
