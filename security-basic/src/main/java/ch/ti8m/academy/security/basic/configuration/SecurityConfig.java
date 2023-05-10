package ch.ti8m.academy.security.basic.configuration;

import ch.ti8m.academy.security.basic.user.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@EnableWebSecurity
public class SecurityConfig {
    private final String[] whiteList = new String[]{
            "/messages/default/open",
            "/h2",
            "/h2/**",
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
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .expressionHandler(webSecurityExpressionHandler());

        http.authorizeRequests()
                // open endponts
                .antMatchers(whiteList).permitAll()
                // role hierarchy definition
                .expressionHandler(webSecurityExpressionHandler())
                // role-protected endoints
                .antMatchers(HttpMethod.DELETE, message).hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, message).hasRole(UserRole.STAFF.name())
                .antMatchers(HttpMethod.PUT, message).hasRole(UserRole.STAFF.name())
                .antMatchers(message).hasRole(UserRole.USER.name())
                // authentication-protected endpoints
                .anyRequest().authenticated();

        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
//        var admin = User.withUsername("admin")
//                .password(encoder.encode("admin-password"))
//                .roles(ADMIN).build();
//        var staff = User.withUsername("staff")
//                .password(encoder.encode("staff-password"))
//                .roles(STAFF).build();
//        var user = User.withUsername("user")
//                .password(encoder.encode("user-password"))
//                .roles(USER).build();
//        return new InMemoryUserDetailsManager(admin, staff, user);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        var expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
//        var definition = "ROLE_ADMIN > ROLE_STAFF > ROLE_USER";
        var hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy(UserRole.getHierarchyDefinition());
        return hierarchy;
    }
}