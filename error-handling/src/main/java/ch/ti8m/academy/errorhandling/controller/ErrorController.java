package ch.ti8m.academy.errorhandling.controller;

import ch.ti8m.academy.errorhandling.configuration.ErrorCode;
import ch.ti8m.academy.errorhandling.configuration.ErrorMessage;
import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import ch.ti8m.academy.errorhandling.exception.CustomNotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
//        try {
//            throw new CustomLockedException();
//        } catch (CustomLockedException e) {
//            throw new ResponseStatusException(
//                    HttpStatus.LOCKED,
//                    "The resource cannot be accesses",
//                    e);
//        }
    }

    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(CustomLockedException.class)
    public ErrorMessage handleLocked() {
        return new ErrorMessage(
                ErrorCode.RESOURCE_LOCKED,
                "The resource cannot be accesses"
        );
    }
}
