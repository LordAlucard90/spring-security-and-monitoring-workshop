package ch.ti8m.academy.errorhandling.controller;

import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import ch.ti8m.academy.errorhandling.exception.CustomNotImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("errors")
public class ErrorController {

    @GetMapping("not-implemented")
    public void notImplemented() {
        throw new CustomNotImplementedException();
    }

    @GetMapping("locked")
    public void locked() {
        // TODO: sorround with a try-catch and return an ResponseStatusException with
        //  - status code: LOCKED
        //  - message: The resource cannot be accessed
        throw new CustomLockedException();
    }
}
