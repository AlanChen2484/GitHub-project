package com.example.service;

import com.example.model.UserInfo;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Object UserInfo(UserInfo user) {
        List<UserInfo> list = userRepository.findByUsername(user.getUsername());
        if (list.size() > 0) {
            return "用户已存在";
        } else {
            userRepository.save(user);
            return "success";//个人信息存储成功
        }
    }
}
