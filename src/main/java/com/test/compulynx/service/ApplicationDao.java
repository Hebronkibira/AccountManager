package com.test.compulynx.service;

import com.test.compulynx.model.ApplicationUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ApplicationDao {
    public Optional<ApplicationUser>loadUserByUsername(String customerId);

}
