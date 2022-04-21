package com.example.tiemchungbe.service;

import com.example.tiemchungbe.model.entity.Account;
import com.example.tiemchungbe.repository.IAccountRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public Account findAccountByUserName(String userName) {
        return accountRepository.findAccountByUserName(userName);
    }

    @Override
    public Integer findIdUserByUserName(String userName) {
        return accountRepository.findIdUserByUserName(userName);
    }

    @Override
    public String existByUserName(String userName) {
        return accountRepository.existByUserName(userName);
    }

    @Override
    public String existsByEmail(String userName) {
        return null;
    }

    @Override
    public void addNew(String userName, String password, Boolean isEnable, String verifiedCode, String email) throws MessagingException, UnsupportedEncodingException {
        String randomCode = RandomString.make(64);
        accountRepository.addNew(userName, password, false, randomCode, email);
//        sendVerificationEmail(userName, randomCode, email);
    }

    @Override
    public void addNew(String username, String password, String email) throws MessagingException, UnsupportedEncodingException {
        String randomCode = RandomString.make(64);
        accountRepository.addNew(username, password, false, randomCode, email);
        sendVerificationEmail(username, randomCode, email);
    }

    private void sendVerificationEmail(String userName, String randomCode, String email) throws MessagingException, UnsupportedEncodingException {
        String subject = "Hãy xác thực email của bạn";
        String mailContent = "";
        String confirmUrl = "http://localhost:4200/verification?code=" + randomCode;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(email);
        helper.setFrom("thai180198@gmail.com", "TRUNG TÂM Y TẾ DỰ PHÒNG THÁI VŨ");
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>" + "<p> Nhấn vào link sau để xác thực email của bạn:</p>" +
                "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>" +
                "<p>TRUNG TÂM Y TẾ DỰ PHÒNG THÁI VŨ</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    @Override
    public Boolean findAccountByVerificationCode(String verifyCode) {
        Account account = accountRepository.findAccountByVerificationCode(verifyCode);
        return account != null;
    }

    @Override
    public void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException {
        String code = RandomString.make(64);
        accountRepository.addVerificationCode(code, username);
        Account account = accountRepository.findAccountByVerificationCode(code);
        this.sendVerificationEmailForResetPassWord(account.getUserName(), code, account.getEmail());
    }

    private void sendVerificationEmailForResetPassWord(String userName, String randomCode, String email) throws MessagingException, UnsupportedEncodingException {
        String subject = "Hãy xác thực email của bạn";
        String mailContent = "";
        String confirmUrl = "http://localhost:4200/verification?code=" + randomCode;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(email);
        helper.setFrom("thai180198@gmail.com", "TRUNG TÂM Y TẾ DỰ PHÒNG THÁI VŨ");
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>" + "<p> Nhấn vào link sau để xác thực email của bạn:</p>" +
                "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>" +
                "<p>TRUNG TÂM Y TẾ DỰ PHÒNG THÁI VŨ</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }

    @Override
    public void addNewAccount(String username, String password) {
        accountRepository.addNewAccount(username, password);
    }

    @Override
    public void saveNewPassword(String password, String code) {
        accountRepository.saveNewPassword(password, code);
    }

    @Override
    public void addVerificationCode(String code, String username) {
        accountRepository.addVerificationCode(code, username);
    }

    @Override
    public Boolean existsById(Integer bookId) {
        return accountRepository.existsById(bookId);
    }

}
