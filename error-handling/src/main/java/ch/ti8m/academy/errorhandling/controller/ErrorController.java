package ch.ti8m.academy.errorhandling.controller;

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
}
