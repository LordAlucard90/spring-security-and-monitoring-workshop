package ch.ti8m.academy.errorhandling.controller;

import ch.ti8m.academy.errorhandling.configuration.LogLevel;
import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import ch.ti8m.academy.errorhandling.exception.CustomNotImplementedException;
import ch.ti8m.academy.errorhandling.exception.GenericApiException;
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
//        try {
//            throw new CustomLockedException();
//        } catch (CustomLockedException e) {
//            throw new ResponseStatusException(
//                    HttpStatus.LOCKED,
//                    "The resource cannot be accesses",
//                    e);
//        }
    }

    @GetMapping("droids")
    public void droids() {
        throw GenericApiException.notFound()
                .withLogLevel(LogLevel.WARN)
                .withInternalMessage("Droids are safe")
                .withUserMessage("These are not the droids you are looking for");
    }
}
