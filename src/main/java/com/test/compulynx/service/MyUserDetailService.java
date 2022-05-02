package com.test.compulynx.service;

import com.test.compulynx.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {


    private final ApplicationDao loadUserByUsername;

    public MyUserDetailService(@Qualifier("fake") ApplicationDao loadUserByUsername) {
        this.loadUserByUsername = loadUserByUsername;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = loadUserByUsername.loadUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User with Username %s nor found", username)));
        System.out.printf(" %s %s ",userDetails.getUsername() , userDetails.getPassword());
        return userDetails;
    }
}
