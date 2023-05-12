package ch.ti8m.academy.errorhandling.configuration;

import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(CustomLockedException.class)
    public ErrorMessage handleLocked() {
        return new ErrorMessage(
                ErrorCode.RESOURCE_LOCKED,
                "The resource cannot be accesses"
        );
    }

    // TODO: add an @ExceptionHandler that manages GenericApiException  and
    //  - returns an ErrorMessage with the define message and error code
    //  - returns the defined status code
    //  - logs the correct message with defined log level

    private void logError(WebRequest request, String internalMessage, LogLevel logLevel) {
        var message = String.format(
                "Unaboe to respond to user '%s' at %s due to error '%s'",
                request.getRemoteUser(),
                request.getDescription(false),
                internalMessage
        );
        switch (logLevel) {
            case INFO:
                log.info(message);
                break;
            case WARN:
                log.warn(message);
                break;
            default:
                log.error(message);
        }
    }
}
