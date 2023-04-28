package ch.ti8m.academy.errorhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(
            value = HttpStatus.NOT_IMPLEMENTED,
            reason = "I'll do it later, maybe.."
    )
    public class CustomNotImplementedException extends RuntimeException {
    }
