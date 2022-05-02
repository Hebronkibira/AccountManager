package com.test.compulynx.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "custGenerator", allocationSize = 1, sequenceName = "custGenerator")
    @GeneratedValue(generator = "custGenerator", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String pin;
    private String firstName;
    private String lastName;
    private String email;
    private String customerId;

    @Transient
    private Set<GrantedAuthority> authorities;

//    @ManyToMany(mappedBy = "currentBalance",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Account> accountList;

    public Customer() {
    }

    public Customer(String customerId, String pin) {
        this.pin = pin;
        this.customerId = customerId;
    }

    public Customer(Long id, String pin, String firstName, String lastName, String email, String customerId) {
        this.id = id;
        this.pin = pin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerId = customerId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return pin;
//    }
//
//    @Override
//    public String getUsername() {
//        return customerId;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }


}
