package ru.job4j.chat.auth.filter;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.job4j.chat.auth.JwtTokenProvider;

/**
 * @author Andrey
 * @version 1
 * @date 2/25/2021
 */

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;
    public JwtConfigurer(JwtTokenProvider jwtTokenProvider) {
            this.jwtTokenProvider = jwtTokenProvider;
            }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
            JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
            httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        }
}
