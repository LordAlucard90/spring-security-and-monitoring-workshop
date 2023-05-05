package ch.ti8m.academy.security.basic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final String[] whiteList = new String[]{
            "/messages/default/open",
    };
    private final String ADMIN = "ADMIN";
    private final String STAFF = "STAFF";
    private final String USER = "USER";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(whiteList)
                .permitAll();

        http.httpBasic();

        http.authorizeHttpRequests()
                .anyRequest()
                .authenticated();

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

    //    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(WebSecurity web) {
//        return (web) -> web.ignoring().antMatchers("/resources/**");
//    }
}
