package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Getter
public class PrincipalUser implements UserDetails {
    private final String userName;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;


    public PrincipalUser(String userName, String email, String password,   Collection<? extends GrantedAuthority> authorities)  {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static PrincipalUser build(UserEntity user, List<RoleEntity> roles) {
        List<GrantedAuthority> list = new ArrayList<>();
        for (RoleEntity rol : roles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(rol.getName());
            list.add(simpleGrantedAuthority);
        }
        List<GrantedAuthority> authorities = Collections.unmodifiableList(list);
        return new PrincipalUser(user.getName(),  user.getEmail(),
                user.getPassword(), authorities);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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