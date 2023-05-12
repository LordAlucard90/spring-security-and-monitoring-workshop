package ch.ti8m.academy.security.basic.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("messages")
public class MessageController {
    // TODO: open for anyone
    @GetMapping("default/open")
    public MessageDto open() {
        return new MessageDto("open to everyone");
    }

    @GetMapping("default/authenticated")
    public MessageDto authenticated() {
        return new MessageDto("open to any authenticated user");
    }

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

    // TODO: secure for ADMIN
    @DeleteMapping("message")
    public MessageDto deleteMessage() {
        return new MessageDto("This endpoint is accessible only to Admin.");
    }

    // TODO: secure for STAFF
    @PostMapping("message")
    public MessageDto createMessage(@RequestBody MessageDto messageDto) {
        log.info("Creation message={}", messageDto.getMessage());
        return new MessageDto("This endpoint is accessible to Staff.");
    }

    // TODO: secure for STAFF
    @PutMapping("message")
    public MessageDto updateMessage(@RequestBody MessageDto messageDto) {
        log.info("Update message={}", messageDto.getMessage());
        return new MessageDto("This endpoint is accessible to Staff.");
    }

    // TODO: secure for USER
    @GetMapping("message")
    public MessageDto getMessage() {
        return new MessageDto("This endpoint is accessible to User.");
    }
}
