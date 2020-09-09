package com.github.bg.admin.web.core.entity;

import lombok.Data;

@Data
public class User {
    public String name;
    public String login_name;
    public String password;
    public String telephone;
    public String email;
    public String department_name;
}
