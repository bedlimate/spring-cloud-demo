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
                        .withTimerDelayInMilliseconds(100)
                )
        );
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
