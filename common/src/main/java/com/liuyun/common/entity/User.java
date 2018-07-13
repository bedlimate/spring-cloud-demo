package com.liuyun.common.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class User {
    //id
    private Integer id;
    //生日
    private LocalDate birthday;
    //姓名
    private String name;
    //当前时间
    private OffsetDateTime now;

}
