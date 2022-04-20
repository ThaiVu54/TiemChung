package com.example.tiemchungbe.service;

import com.example.tiemchungbe.model.entity.Account;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IAccountService {
    Account findAccountByUserName(String userName);

    Integer findIdUserByUserName(String userName);

    String existByUserName(String userName);

    String existsByEmail(String userName);

    void addNew(String userName, String password, Boolean isEnable, String verifiedCode, String email) throws MessagingException, UnsupportedEncodingException;

    Boolean findAccountByVerificationCode(String verifyCode);

    void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException;

    List<Account> getAllAccount();

    void addNewAccount(String username, String password);

    void saveNewPassword(String password, String code);

    void addVerificationCode(String code, String username);

    Boolean existsById(Integer bookId);

//    void addNew(String username, String password);
}
