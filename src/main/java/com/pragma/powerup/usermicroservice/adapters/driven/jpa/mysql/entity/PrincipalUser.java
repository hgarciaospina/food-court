package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Getter
public class PrincipalUser implements UserDetails {
    private final String email;
    private final String role;
    private final String password;

    public PrincipalUser(String email, String role, String password) {
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public static PrincipalUser build(UserEntity user, String role, String password) {
        return new PrincipalUser(user.getEmail(), role, user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new  SimpleGrantedAuthority(role));
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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
        return true;
    }
}