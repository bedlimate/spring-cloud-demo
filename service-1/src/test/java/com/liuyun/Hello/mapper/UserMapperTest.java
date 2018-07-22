package com.liuyun.Hello.mapper;

import com.liuyun.common.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setId(400);
        user.setName("spring-boot-test");
        user.setBirthday(LocalDate.now());
        user.setNow(OffsetDateTime.now());
        int rows = userMapper.saveUser(user);
        Assert.assertEquals(1, rows);
    }

    @Test
    public void getUsersTest() {
        List<Integer> userIds = new ArrayList<>(4);
        userIds.add(100);
        List<User> users = userMapper.getUsers(userIds);
        Assert.assertEquals(1, users.size());
    }
}
