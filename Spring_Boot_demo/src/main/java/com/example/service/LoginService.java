package com.example.service;

import com.example.model.UserLogin;
import com.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public Object Login(UserLogin user){
        List<UserLogin> list = loginRepository.findByUsername(user.getUsername());
        if(list.size()>0){
            return "登录成功";
        }
        else
        {
            return "没有该用户信息";
        }
    }
}
