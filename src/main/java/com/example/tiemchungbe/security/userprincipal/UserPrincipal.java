package com.example.tiemchungbe.security.userprincipal;

import com.example.tiemchungbe.model.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private Boolean enabled;
    @JsonIgnore
    private String password;
    List<GrantedAuthority> authorities = null;

    public UserPrincipal(Integer id, String username, Boolean enabled, String password, List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.enabled = enabled;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal build(Account account) {
        List<GrantedAuthority> autheorities = account.getAccountRoleList().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                .collect(Collectors.toList());
        return new UserPrincipal(
                account.getAccountId(),
                account.getUserName(),
                account.getEnabled(),
                account.getEncryptPw(),
                autheorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserPrincipal userPrincipal = (UserPrincipal) o;
        return Objects.equals(id, userPrincipal.id);
    }
}
