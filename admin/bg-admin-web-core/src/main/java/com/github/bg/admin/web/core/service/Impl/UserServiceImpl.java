package com.github.bg.admin.web.core.service.Impl;

import com.github.bg.admin.web.core.constant.SystemStaticConst;
import com.github.bg.admin.web.core.dao.UserMapper;
import com.github.bg.admin.web.core.entity.Page;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import com.github.bg.admin.web.core.entity.User;
import com.github.bg.admin.web.core.service.UserService;
import com.github.bg.admin.web.core.utils.PageUtil;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnInfo findUser(Map map) {
        User user = userMapper.findUser(map);
        if (user != null){
            return new ReturnInfo(SystemStaticConst.SUCCESS, "登录成功！",user);
        }else {
            return new ReturnInfo(SystemStaticConst.FAIL, "登录失败！");
        }
    }
}
