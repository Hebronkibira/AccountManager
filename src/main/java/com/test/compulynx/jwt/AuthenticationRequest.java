package com.test.compulynx.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthenticationRequest {

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String customerId, String pin) {
        this.customerId = customerId;
        this.pin = pin;
    }

    private String customerId;
    private String pin;
}
