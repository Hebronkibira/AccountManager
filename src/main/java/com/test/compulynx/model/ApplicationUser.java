package com.test.compulynx.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class ApplicationUser implements  UserDetails {
    private Long id;
    private String customerId;
    private String pin;
    private Set<GrantedAuthority> authorities;

    public ApplicationUser() {
    }

    public ApplicationUser(String customerId, String pin, Set<GrantedAuthority> authorities) {
        this.customerId = customerId;
        this.pin = pin;
        this.authorities = authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPin() {
        return pin;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return customerId;
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
