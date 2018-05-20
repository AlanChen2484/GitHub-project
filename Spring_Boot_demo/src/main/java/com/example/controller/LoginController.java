package com.example.controller;

import com.example.model.UserLogin;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    public Object Login(@RequestBody(required = false) UserLogin user) {
        return loginService.Login(user);
    }
//    @GetMapping(value = "/hello")
//    public String Hello(){
//        return "hello";
//    }
}


