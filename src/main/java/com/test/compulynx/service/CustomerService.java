package com.test.compulynx.service;

import com.test.compulynx.model.Account;
import com.test.compulynx.model.Customer;
import com.test.compulynx.repository.AccountRepository;
import com.test.compulynx.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private  final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAllCustomers().get();
    }

    @Transactional
    public Customer registerCustomer(Customer customer){
        Account account= new Account(0.0,"Savings",new Date(),"",customer.getCustomerId());
        customer.setPin(passwordEncoder.encode(this.generatePin()));
        customerRepository.save(customer);
        accountRepository.save(account);

        return customer;
    }

    public Boolean findCustomerByCustomerId(String customerId){
        return customerRepository.findCustomerByCustomerId(customerId).isPresent();
    }
    private String generatePin(){
        SecureRandom secureRandom = new SecureRandom();
        String pin=String .valueOf(secureRandom.nextInt(10000109)).substring(1,5); //TODO make the generator to always return a 4 digit number
        System.out.println("Generated customer pin "+pin);
        return pin;
    }
}
