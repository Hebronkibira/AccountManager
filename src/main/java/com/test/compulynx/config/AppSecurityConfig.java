package com.test.compulynx.config;

import com.test.compulynx.jwt.JwtAuthenticationFilter;
import com.test.compulynx.jwt.JwtUsernamePasswordFilter;
import com.test.compulynx.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@EnableWebMvc
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailService myUserDetailService;
    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(MyUserDetailService myUserDetailService, PasswordEncoder passwordEncoder) {

        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordFilter(authenticationManager()))
                .addFilterAfter(new JwtAuthenticationFilter(),JwtUsernamePasswordFilter.class)
                .authorizeRequests()
                .antMatchers("/public/**")
                .permitAll()
                .anyRequest()
                .authenticated();
//                .and()
//                .formLogin().successForwardUrl("/").failureForwardUrl("/login");
    }


//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder().
//                username("Admin").
//                password(passwordEncoder().encode("password123"))
//                .authorities("ADMIN")
//                .roles("")
//                .build();
//        return new InMemoryUserDetailsManager(
//                userDetails
//        );
//    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(myUserDetailService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
}
