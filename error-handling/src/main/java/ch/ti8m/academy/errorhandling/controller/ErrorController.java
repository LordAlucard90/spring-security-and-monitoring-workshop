package ch.ti8m.academy.errorhandling.controller;

import ch.ti8m.academy.errorhandling.configuration.ErrorCode;
import ch.ti8m.academy.errorhandling.configuration.ErrorMessage;
import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import ch.ti8m.academy.errorhandling.exception.CustomNotImplementedException;
import ch.ti8m.academy.errorhandling.exception.CustomTooEarlyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("errors")
public class ErrorController {
    /*
        Exceptions annotated with @ResponseStatus
        test request -> Default Exception Error
     */
    @GetMapping("not-implemented")
    public void notImplemented() {
        throw new CustomNotImplementedException();
    }

    /*
        Using @ExceptionHandler at the controller level
        test request -> Controller Exception Handler
     */
    @GetMapping("locked")
    public void locked() {
        throw new CustomLockedException();
    }

    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(CustomLockedException.class)
//    public ErrorMessage handleLocked(CustomLockedException exception, HttpServletRequest request) {
//    public ErrorMessage handleLocked(CustomLockedException exception) {
//    public ErrorMessage handleLocked(HttpServletRequest request) {
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
}
