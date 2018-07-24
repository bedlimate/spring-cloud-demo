package com.liuyun.springcloudhystrix;


import com.liuyun.common.entity.User;
import com.liuyun.springcloudhystrix.hystrix.UserCollapseCommand;
import com.liuyun.springcloudhystrix.service.IUserService;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CollspedTest {

    @Autowired
    IUserService userService;


    @Test
    public void getUsersTest() {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        int times = 100;
        final Random random = new Random();
        final List<Future<User>> futures = new ArrayList<>(times);
        for(int i = 0; i < times; i++){
            int userId = (random.nextInt(5) + 1) * 100;
            UserCollapseCommand collapseCommand = new UserCollapseCommand(userService, userId);
            Future<User> future = collapseCommand.queue();
            futures.add(future);
        }

        futures.forEach(
                future -> {
                    try {
                        System.out.println("user: " + future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );

        context.close();
    }
}
