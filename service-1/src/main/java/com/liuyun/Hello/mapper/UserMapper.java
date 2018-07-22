package com.liuyun.Hello.mapper;

import com.liuyun.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int saveUser(User user);

    List<User> getUsers(@Param("userIds") List<Integer> ids);
}
