package ch.ti8m.academy.security.basic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    private final String[] whiteList = new String[]{
            "/messages/default/open",
    };
    private final String ADMIN = "ADMIN";
    private final String STAFF = "STAFF";
    private final String USER = "USER";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();

        // TODO: secure the endpoints with these roles
        //   DELETE /message: ADMIN
        //   POST   /message: STAFF
        //   PUT    /message: STAFF
        //   GET    /message: USER
        http.authorizeRequests()
                // open endpoints
                .antMatchers(whiteList).permitAll()
                // authentication-protected endpoints
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        var admin = User.withUsername("admin")
                .password(encoder.encode("admin-password"))
                .roles(ADMIN, STAFF, USER).build();
        var staff = User.withUsername("staff")
                .password(encoder.encode("staff-password"))
                .roles(STAFF, USER).build();
        var user = User.withUsername("user")
                .password(encoder.encode("user-password"))
                .roles(USER).build();
        return new InMemoryUserDetailsManager(admin, staff, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
