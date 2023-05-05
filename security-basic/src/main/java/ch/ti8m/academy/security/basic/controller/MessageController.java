package ch.ti8m.academy.security.basic.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("messages")
public class MessageController {
    /*
        Accessible to anyone
        Exercise #1
     */
    @GetMapping("default/open")
    public ResponseMessage open() {
        return new ResponseMessage("open to everyone");
    }

    /*
        Accessible to any authenticated user
        Exercise #2
     */
    @GetMapping("default/authenticated")
    public ResponseMessage authenticated() {
        return new ResponseMessage("open to any authenticated user");
    }

    /*
        Accessible to anyone
        test request on each user type -> Roles
     */
    @GetMapping("default/roles")
    public ResponseMessage roles() {
        var roles = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new ResponseMessage("The user has the following roles: " + roles);
    }
}
