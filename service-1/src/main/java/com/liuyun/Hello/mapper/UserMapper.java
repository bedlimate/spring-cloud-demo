package com.liuyun.Hello.mapper;

import com.liuyun.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int saveUser(User user);
}
