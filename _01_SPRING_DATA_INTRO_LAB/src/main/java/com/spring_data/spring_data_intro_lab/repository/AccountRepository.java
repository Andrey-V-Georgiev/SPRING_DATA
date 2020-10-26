package com.spring_data.spring_data_intro_lab.repository;

import com.spring_data.spring_data_intro_lab.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountById(Long id);
}
