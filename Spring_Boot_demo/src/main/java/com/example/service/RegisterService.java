package com.example.service;

import com.example.model.UserLogin;
import com.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    @Autowired
    private LoginRepository loginRepository;

    public Object Register(UserLogin user) {
        List<UserLogin> list = loginRepository.findByUsername(user.getUsername());
        if (list.size() > 0) {
            return "账号已存在";
        } else {
            loginRepository.save(user);
            return "success";//注册成功
        }
    }

}

