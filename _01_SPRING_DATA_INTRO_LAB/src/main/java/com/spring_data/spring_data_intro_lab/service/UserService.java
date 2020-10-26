package com.spring_data.spring_data_intro_lab.service;

import com.spring_data.spring_data_intro_lab.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void registerUser(User user);
}
