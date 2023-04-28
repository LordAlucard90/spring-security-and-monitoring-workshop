package ch.ti8m.academy.security.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("messages")
public class MessageController {
    /*
        Accessible to anyone
        test request on each user type -> Open
     */
    @GetMapping("open")
    public ResponseMessage open() {
        return new ResponseMessage("open to everyone");
    }
}
