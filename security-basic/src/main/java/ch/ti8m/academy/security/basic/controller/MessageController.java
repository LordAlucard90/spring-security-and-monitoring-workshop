package ch.ti8m.academy.security.basic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("messages")
public class MessageController {
    /*
        Accessible to anyone
        Exercise #1
     */
    @GetMapping("default/open")
    public MessageDto open() {
        return new MessageDto("open to everyone");
    }

    /*
        Accessible to any authenticated user
        Exercise #2
     */
    @GetMapping("default/authenticated")
    public MessageDto authenticated() {
        return new MessageDto("open to any authenticated user");
    }

    /*
        See roles configuration
        Exercise #4
     */
    @GetMapping("default/roles")
    public MessageDto roles() {
        var roles = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new MessageDto("The user has the following roles: " + roles);
    }

    /*
        Secure Endpoints With Roles
        Exercise #5
     */
    @DeleteMapping("message")
    public MessageDto deleteMessage() {
        return new MessageDto("This endpoint is accessible only to Admin.");
    }

    @PostMapping("message")
    public MessageDto createMessage(@RequestBody MessageDto messageDto) {
        log.info("Creation message={}", messageDto.getMessage());
        return new MessageDto("This endpoint is accessible to Staff.");
    }

    @PutMapping("message")
    public MessageDto updateMessage(@RequestBody MessageDto messageDto) {
        log.info("Update message={}", messageDto.getMessage());
        return new MessageDto("This endpoint is accessible to Staff.");
    }

    @GetMapping("message")
    public MessageDto getMessage() {
        return new MessageDto("This endpoint is accessible to User.");
    }
}
