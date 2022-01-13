package org.petstore.service;

import org.petstore.domain.Account;
import org.petstore.persistence.impl.AccountDAOImpl;

public class AccountService {

    public Account getAccount(String username) {
        return new AccountDAOImpl().getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return new AccountDAOImpl().getAccountByUsernameAndPassword(account);
    }

    public void insertAccount(Account account) {
        new AccountDAOImpl().insertAccount(account);
        new AccountDAOImpl().insertProfile(account);
        new AccountDAOImpl().insertSignon(account);
    }

    public void updateAccount(Account account) {
        new AccountDAOImpl().updateAccount(account);
        new AccountDAOImpl().updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            new AccountDAOImpl().updateSignon(account);
        }
    }
}
