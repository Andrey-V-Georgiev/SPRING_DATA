package com.spring_data.spring_data_intro_lab.service.impl;

import com.spring_data.spring_data_intro_lab.exception.IllegalBankOperationException;
import com.spring_data.spring_data_intro_lab.exception.IllegalRegistrationException;
import com.spring_data.spring_data_intro_lab.model.User;
import com.spring_data.spring_data_intro_lab.repository.UserRepository;
import com.spring_data.spring_data_intro_lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        Optional<User> userCheck = userRepository.findById(user.getId());
        if(userCheck.isPresent()) {
            throw new IllegalRegistrationException("User with this id already exists");
        }
        userRepository.save(user);
    }
}
