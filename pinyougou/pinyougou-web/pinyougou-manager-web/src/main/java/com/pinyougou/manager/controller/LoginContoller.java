package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/** 获取登录用户名控制器 */
@RestController
public class LoginContoller {
    /** 显示登录用户名 */
    @RequestMapping("showLoginName")
    public Map<String,String> showLoginName(){
        // 获取登录用户名
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,String> data = new HashMap<>();
        data.put("loginName",loginName );
        return data;
    }
}
