package com.example.controller;

import com.example.model.UserInfo;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/userinfo")
    public Object UserInfo(@RequestBody(required = false) UserInfo user) {
        return userService.UserInfo(user);
    }

}

