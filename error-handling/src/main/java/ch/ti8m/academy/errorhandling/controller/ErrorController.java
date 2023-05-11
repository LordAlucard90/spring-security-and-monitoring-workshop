package ch.ti8m.academy.errorhandling.controller;

import ch.ti8m.academy.errorhandling.configuration.ErrorCode;
import ch.ti8m.academy.errorhandling.configuration.ErrorMessage;
import ch.ti8m.academy.errorhandling.configuration.LogLevel;
import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import ch.ti8m.academy.errorhandling.exception.CustomNotImplementedException;
import ch.ti8m.academy.errorhandling.exception.CustomTooEarlyException;
import ch.ti8m.academy.errorhandling.exception.GenericApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(
        value = "errors",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ErrorController {

    @GetMapping("not-implemented")
    public void notImplemented() {
        throw new CustomNotImplementedException();
    }

//    @GetMapping("teapot")
//    public void teapot() {
////        try {
//            throw new IllegalArgumentException("Coffee");
////        } catch (IllegalArgumentException exception) {
////            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT,
////                    "You cannot ask for coffee",
////                    exception
////            );
////        }
//    }

    @GetMapping("locked")
    public void locked() {
        try {
            throw new CustomLockedException();
        } catch (CustomLockedException e) {
            throw new ResponseStatusException(
                    HttpStatus.LOCKED,
                    "The resource cannot be accesses",
                    e);
        }
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

    @GetMapping(value = "droids")
    public void droids() {
        throw GenericApiException.notFound()
                .withLogLevel(LogLevel.WARN)
                .withInternalMessage("Droids are safe")
                .withUserMessage("These are not the droids you are looking for");
    }
}
