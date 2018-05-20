package com.example.repository;

import com.example.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<UserLogin, Integer> {

    public List<UserLogin> findByUsername(String username);

}
