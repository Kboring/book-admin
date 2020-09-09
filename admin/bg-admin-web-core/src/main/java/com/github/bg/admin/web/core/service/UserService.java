package com.github.bg.admin.web.core.service;

import com.github.bg.admin.web.core.entity.ReturnInfo;

import java.util.Map;

public interface UserService {
    ReturnInfo findUser(Map map);
}
