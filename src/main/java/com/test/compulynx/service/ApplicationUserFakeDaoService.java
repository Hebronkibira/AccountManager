package com.test.compulynx.service;

import com.test.compulynx.model.ApplicationUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository("fake")
public class ApplicationUserFakeDaoService implements ApplicationDao {
    private final PasswordEncoder passwordEncoder;

    public ApplicationUserFakeDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Set<ApplicationUser> getApplicationUsers() {
        Set<ApplicationUser> users = Set.of(
                new ApplicationUser("ADMIN", passwordEncoder.encode("8009"), new HashSet<>()),
                new ApplicationUser("george", passwordEncoder.encode("george123"), new HashSet<>()),
                new ApplicationUser("anonymous", passwordEncoder.encode("anonymous123"), new HashSet<>())
        );
        System.out.println("Size of list of Users " + users);
        System.out.println(users.stream().count());
        return users;
    }

    @Override
    public Optional<ApplicationUser> loadUserByUsername(String customerId) {
        Optional<ApplicationUser> opt = this.getApplicationUsers()
                .stream(). filter(Appuser -> {
                   // System.out.println("AppUser Username "+Appuser.getUsername());
                    return Appuser.getUsername().equals(customerId);}).findFirst();
        return opt;
    }
}
