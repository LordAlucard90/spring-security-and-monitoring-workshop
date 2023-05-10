package ch.ti8m.academy.errorhandling.controller;

import ch.ti8m.academy.errorhandling.configuration.ErrorCode;
import ch.ti8m.academy.errorhandling.configuration.ErrorMessage;
import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import ch.ti8m.academy.errorhandling.exception.CustomNotImplementedException;
import ch.ti8m.academy.errorhandling.exception.CustomTooEarlyException;
import ch.ti8m.academy.errorhandling.exception.GenericApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("errors")
public class ErrorController {

    @GetMapping("not-implemented")
    public void notImplemented() {
        throw new CustomNotImplementedException();
    }

    @GetMapping("teapot")
    public void teapot() {
        try {
            throw new IllegalArgumentException("Coffee");
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT,
                    "You cannot ask for coffee",
                    exception
            );
        }
    }

    @GetMapping("locked")
    public void locked() {
        throw new CustomLockedException();
    }

    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(CustomLockedException.class)
    public ErrorMessage handleLocked() {
        return new ErrorMessage(
                ErrorCode.RESOURCE_LOCKED,
                "The resource cannot be accesses"
        );
    }

    /*
        Using @RestControllerAdvice
        test request -> ControllerAdvice Handler
     */
    @GetMapping("too-early")
    public void tooEarly() {
        throw new CustomTooEarlyException("Five more minutes please.");
    }

    /*
        Extending ResponseEntityExceptionHandler
        test request -> Extend Resopnse Entity Handler
     */
    @GetMapping(value = "not-supported", produces = MediaType.APPLICATION_JSON_VALUE)
    public void notSupportedMediaType() {
        throw new RuntimeException("This should not be reached.");
    }

    /*
        Using a custom exception in conbination with the ControllerAdvice
        test request -> Use Generic Exception
     */
    @GetMapping(value = "failed-dependency")
    public void failedDependency() {
        var interalMessage = "No service associated that can handle the request";
        throw GenericApiException
                .failedDependency()
                .withInternalMessage(interalMessage);
    }
}
