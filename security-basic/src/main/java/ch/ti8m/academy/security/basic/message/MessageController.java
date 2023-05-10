package ch.ti8m.academy.security.basic.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("messages")
public class MessageController {
    /*
        Accessible to anyone
        Exercise #1
     */
    @PreAuthorize("permitAll()")
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
        Exercise #4 part 2
        Exercise #5
        Exercise #6
     */
    @PostAuthorize("hasRole('ADMIN')")
    @DeleteMapping("message")
    public MessageDto deleteMessage() {
        return new MessageDto("This endpoint is accessible only to Admin.");
    }

    @Secured("ROLE_STAFF")
    @PostMapping("message")
    public MessageDto createMessage(@RequestBody MessageDto messageDto) {
        log.info("Creation message={}", messageDto.getMessage());
        return new MessageDto("This endpoint is accessible to Staff.");
    }

    @RolesAllowed("ROLE_STAFF")
    @PutMapping("message")
    public MessageDto updateMessage(@RequestBody MessageDto messageDto) {
        log.info("Update message={}", messageDto.getMessage());
        return new MessageDto("This endpoint is accessible to Staff.");
    }

    @PostAuthorize("hasRole('USER')")
    @GetMapping("message")
    public MessageDto getMessage() {
        return new MessageDto("This endpoint is accessible to User.");
    }
}
