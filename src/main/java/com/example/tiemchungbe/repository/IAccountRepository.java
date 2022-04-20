package com.example.tiemchungbe.repository;

import com.example.tiemchungbe.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountByUserName(String userName);

    @Query(value = "select account_id from vaccine_management1.account where user_name = ?1", nativeQuery = true)
    Integer findIdUserByUserName(String userName);

    @Query(value = "select user_name from vaccine_management1.account where user_name = ?1", nativeQuery = true)
    String existByUserName(String userName);

    @Query(value = "select email from vaccine_manager1.account where email = ?1", nativeQuery = true)
    String existsByEmail(String userName);

    @Modifying
    @Query(value = "insert into account (email, user_name, encrypt_pw, is_enabled, verification_code) value (?1,?2,?3,?4,?5);", nativeQuery = true)
    void addNew(String userName, String password, Boolean isEnable, String verifiedCode, String email);

    Account findAccountByVerificationCode(String verifyCode);

    @Modifying
    @Query(value = "insert into account (verification_code) value (?1);", nativeQuery = true)
    void addVerificationCode(String verifyCode);

    @Modifying
    @Query(value ="update account set verification_code=?1 where user_name =?2",nativeQuery = true)
    void addVerificationCode(String code,String username);

    @Query(value = "select * from account", nativeQuery = true)
    List<Account> getAllAccount();

    @Modifying
    @Query(value = "insert into account(user_name,encrypt_pw) values (?1,?2)", nativeQuery = true)
    void addNewAccount(String username, String password);

    @Modifying
    @Query(value = "update account set encrypt_pw =?1,verification_code=null where verification_code=?2 ",nativeQuery = true)
    void saveNewPassword(String password, String code);

}
