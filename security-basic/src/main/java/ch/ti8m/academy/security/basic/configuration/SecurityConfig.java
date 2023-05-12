package ch.ti8m.academy.security.basic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    private final String[] whiteList = new String[]{
            "/messages/default/open",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();

        http.authorizeRequests()
                // open endpoints
                .antMatchers(whiteList).permitAll()
                // authentication-protected endpoints
                .anyRequest().authenticated();

        return http.build();
    }
}
