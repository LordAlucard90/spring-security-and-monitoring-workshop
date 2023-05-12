package ch.ti8m.academy.security.basic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    private final String[] whiteList = new String[]{
            "/messages/default/open",
    };
    private final String[] message = new String[]{
            "/messages/message",
    };
    private final String ADMIN = "ADMIN";
    private final String STAFF = "STAFF";
    private final String USER = "USER";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();

        http.authorizeRequests()
                // open endponts
                .antMatchers(whiteList).permitAll()
                // authentication-protected endpoints
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        // TODO: add users
        //   admin, password: admin-password
        //   user,  password: user-password
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
