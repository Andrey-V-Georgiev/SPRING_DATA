package com.spring_data.spring_data_intro_lab.repository;

import com.spring_data.spring_data_intro_lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
