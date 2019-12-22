package com.jawadaz.springsecurity01.service;

import com.jawadaz.springsecurity01.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    public Optional<User> findUserByUserName(String userName);

    public void save(User user) throws Exception;
}
