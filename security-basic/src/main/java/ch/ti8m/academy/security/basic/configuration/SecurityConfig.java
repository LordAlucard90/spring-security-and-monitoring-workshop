package ch.ti8m.academy.security.basic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final String[] whiteList = new String[]{
            "/messages/open"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers(whiteList).permitAll();
        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(WebSecurity web) {
//        return (web) -> web.ignoring().antMatchers("/resources/**");
//    }
}
