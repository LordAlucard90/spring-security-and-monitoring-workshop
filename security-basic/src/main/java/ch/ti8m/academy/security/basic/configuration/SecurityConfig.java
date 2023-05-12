package ch.ti8m.academy.security.basic.configuration;

import ch.ti8m.academy.security.basic.user.UserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {
    private final String[] whiteList = new String[]{
            "/h2",
            "/h2/**",
    };
    private final String[] message = new String[]{
            "/messages/message",
    };


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
                // authentication-protected endpoints
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        var expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        var hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy(UserRoles.getHierarchyDefinition());
        return hierarchy;
    }
}
