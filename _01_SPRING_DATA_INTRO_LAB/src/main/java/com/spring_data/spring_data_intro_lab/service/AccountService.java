package com.spring_data.spring_data_intro_lab.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountService {
    void withdrawMoney(BigDecimal money, Long id);

    void depositMoney(BigDecimal money, Long id);

    void transferMoney(long fromId, long toId, BigDecimal amount);
}
