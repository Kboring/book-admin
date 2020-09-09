package com.github.bg.admin.web.core.controller;

import com.github.bg.admin.web.core.constant.SystemStaticConst;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import com.github.bg.admin.web.core.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linzf
 * @since 2019/4/25
 * 类描述：
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述：模拟实现用户登录
     * @param loginAccount 用户账号
     * @param loginPassword 用户密码
     * @return 返回登录结果
     */
    @ApiOperation(value = "实现用户登陆")
    @PostMapping("login")
    public ReturnInfo login(@RequestParam(name = "loginAccount") String loginAccount, @RequestParam(name = "loginPassword") String loginPassword){
        Map<String,Object> result = new HashMap<>();
        result.put("loginAccount",loginAccount);
        result.put("loginPassword",loginPassword);
        return userService.findUser(result);
    }

}
