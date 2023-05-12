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
//        try {
//            throw new CustomLockedException();
//        } catch (CustomLockedException e) {
//            throw new ResponseStatusException(
//                    HttpStatus.LOCKED,
//                    "The resource cannot be accessed",
//                    e);
//        }
    }

    @GetMapping("droids")
    public void droids() {
        // TODO: throw a GenericApiException
        //    Status code: NOT_FOUND
        //    Logged message: Droids are safe
        //    Logging Level: WARNING
        //    Response Message: "These are not the droids you are looking for"
        //    Response Code: NOTING_HERE
    }
}
