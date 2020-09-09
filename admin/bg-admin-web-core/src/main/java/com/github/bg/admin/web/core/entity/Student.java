package com.github.bg.admin.web.core.entity;


import lombok.Data;

import java.util.Date;

//下面的注解，可以自动生成get,set方法
@Data
public class Student {
    public Long id;
    public String name;
    public Double score;
    public Date birthday;
}
