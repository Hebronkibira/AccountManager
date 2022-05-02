package com.test.compulynx.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class Extracts and verifies the authenticity of the token from the request header before each request is honoured. If the token is valid and not expired the request continues, otherwise, an exception is thrown
 * It extends the OncePerRequestFilter such so that it is called only once for each request
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");
        System.out.println("Attempting the verification of token "+jwtToken.replace("Bearer ",""));
        //.replace("Bearer ", "");// Remove the Bearer from the token to get the token only
        String key = "SigNingKeyThatWILLbeUSedItoSigntETheTOKENSHOuldBElongANdSEcurE";
        //check if the Jwt starts with "Bearer ";
        if (!jwtToken.startsWith("Bearer ")) {
            //not a valid jwt
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = jwtToken.replace("Bearer", "");

        verifyJwtToken(jwtToken, key);

        filterChain.doFilter(request, response);

    }

    private void verifyJwtToken(String jwtToken, String key) {
        try {
            Jws<Claims> claimsJwt = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(jwtToken);
//            Jws<Claims> claimsJwt = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8)))
//                    .parseClaimsJws(jwtToken);
            Claims claims = claimsJwt.getBody();
            String subject = claims.getSubject();
            //Extract the list of granted authorities(List of a map) form the claims
            List<Map<String, String>> authorities = (List<Map<String, String>>) claims.get("authorities");
            Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(auth -> new SimpleGrantedAuthority(auth.get("authority"))).collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    subject, null, grantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            //catch any exception that might occur
            throw new IllegalStateException(String.format("Token %s can no be verified ", jwtToken));
        }
    }


}
