package com.example.controller;

import com.example.model.UserLogin;
import com.example.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping(value = "/register")
    public Object Register(@RequestBody(required = false) UserLogin user) {
        return registerService.Register(user);
    }

}
