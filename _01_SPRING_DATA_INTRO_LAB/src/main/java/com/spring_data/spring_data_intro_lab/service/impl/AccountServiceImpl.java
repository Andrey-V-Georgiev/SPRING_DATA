package com.spring_data.spring_data_intro_lab.service.impl;

import com.spring_data.spring_data_intro_lab.exception.IllegalBankOperationException;
import com.spring_data.spring_data_intro_lab.model.Account;
import com.spring_data.spring_data_intro_lab.repository.AccountRepository;
import com.spring_data.spring_data_intro_lab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        /* Check for enough available money */
        Account account = accountRepository.findAccountById(id);
        BigDecimal balance = account.getBalance();
        if (balance.compareTo(money) < 0) {
            throw new IllegalBankOperationException(String.format(
                    "Current balance %s is not sufficient for withdraw of %s",
                    account.getBalance(),
                    money
            ));
        }
        /* If enough money in the account */
        account.setBalance(balance.subtract(money));
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Account account = accountRepository.findAccountById(id);
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.add(money));
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void transferMoney(long fromId, long toId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalBankOperationException("Amount can't be with negative value");
        }
        depositMoney(amount, toId);
        withdrawMoney(amount, fromId);
    }
}
