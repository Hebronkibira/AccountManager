package com.test.compulynx.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author George Kibira
 * This Class authenticates a user and if sucessfully authenticates a JWTToken is generated and set in the htpp header
 * It extends the UsernamePasswordFilter class to enable authentication
 */

@AllArgsConstructor
public class JwtUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        setUsernameParameter("username");
        Authentication authenticated = null;
        try {
            System.out.println("Attempting Authentication.... ");
            AuthenticationRequest authenticationRequest = new ObjectMapper().
                    readValue(request.getInputStream(), AuthenticationRequest.class);
            System.out.printf("Attempting Authentication. of ...Username  %s and password %s   ",  authenticationRequest.getCustomerId(), authenticationRequest.getPin());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getCustomerId(), authenticationRequest.getPin()
            );
            authenticated = authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            new IllegalAccessException();
        }
        //   System.out.println("Is Authenticated ?  " + authenticated.isAuthenticated());
        return authenticated;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("Successfully Authenticated >>>>>> attempting to generate token----");
        //Create a jwt token here . You need the jwt api dependencies and its implementation defined in the POM.xml file
        String key = "SigNingKeyThatWILLbeUSedItoSigntETheTOKENSHOuldBElongANdSEcurE";
        //Use the builder Jwt pattern to create a jwt string (jwt token)

        String jwtToken = Jwts.builder().setSubject(authResult.getName()) //get the name of the authentication object and set it as the subject
                .claim("authorities", authResult.getAuthorities()) //get authorities from the authenticated user
                .setIssuedAt(new Date()) //set the date created
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2))) //set the expiration date for the token
                .signWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8))) //Sign your token using a specified key
                .compact();
        // Add the Jwt to the
        response.addHeader("Authorization", "Bearer " + jwtToken);

    }
}
