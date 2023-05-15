package com.example.be.service;

import com.example.be.model.Account;

public interface IAccountService {
    Account findAccountByEmail(String email);
    boolean checkOldPassword(String oldPassword, String password);


    void updateAccount(Account account);
    void addAccount(Account account);
}
