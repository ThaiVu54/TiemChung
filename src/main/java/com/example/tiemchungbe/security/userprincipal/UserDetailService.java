package com.example.tiemchungbe.security.userprincipal;

import com.example.tiemchungbe.model.entity.Account;
import com.example.tiemchungbe.repository.IAccountRepository;
import com.example.tiemchungbe.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IAccountRepository accountRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUserName(username);
        if (account == null ){
            throw new UsernameNotFoundException("User "+ username+" was not found in the database");
        }
        return UserPrincipal.build(account);
    }
}
