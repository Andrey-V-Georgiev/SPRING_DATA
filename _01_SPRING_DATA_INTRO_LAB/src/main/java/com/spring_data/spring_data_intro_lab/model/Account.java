package com.spring_data.spring_data_intro_lab.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


@Entity
@Table(name = "accounts")
public class Account {

    private Long id;
    private BigDecimal balance;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Min(0)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @ManyToOne()
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}