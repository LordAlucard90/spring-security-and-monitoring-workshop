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
        throw new CustomLockedException();
    }

    // TODO: add a method annotated with @ExceptionHandler that
    //  - handles CustomLockedException
    //  - returns status code LOCKED
    //  - returns an ErrorMessage with:
    //      - code: RESOURCE_LOCKED
    //      - message: The resource cannot be accessed
}
