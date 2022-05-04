package com.test.compulynx.service;

import com.test.compulynx.model.Customer;
import com.test.compulynx.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final ApplicationDao loadUserByUsername;

    public MyUserDetailService(CustomerRepository customerRepository, @Qualifier("fake") ApplicationDao loadUserByUsername) {
        this.customerRepository = customerRepository;
        this.loadUserByUsername = loadUserByUsername;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        UserDetails userDetails = loadUserByUsername.loadUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User with Username %s nor found", username)));
//        System.out.printf(" Username ==> %s pin ===> %s ",userDetails.getUsername() , userDetails.getPassword());
//        return userDetails;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByCustomerId(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User with Username %s nor found", username)));
        System.out.printf("Username ==> %s pin ===> %s ",customer.getCustomerId() , customer.getPin());

        return new User(customer.getCustomerId(),customer.getPin(), new HashSet<>());
    }
}
