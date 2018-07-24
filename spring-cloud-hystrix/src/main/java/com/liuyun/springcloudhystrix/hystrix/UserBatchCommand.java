package com.liuyun.springcloudhystrix.hystrix;

import com.liuyun.common.entity.User;
import com.liuyun.springcloudhystrix.service.IUserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.List;

public class UserBatchCommand extends HystrixCommand<List<User>> {

    private IUserService userService;
    private List<Integer> userIds;

    public UserBatchCommand(IUserService userService, List<Integer> userIds){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CollapsingUserGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CollapsingUserKey")));
        this.userService = userService;
        this.userIds = userIds;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.getUsers(userIds);
    }
}
