package ch.ti8m.academy.errorhandling.configuration;

import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import ch.ti8m.academy.errorhandling.exception.GenericApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(GenericApiException.class)
    public ResponseEntity<ErrorMessage> handleGenericApiException(GenericApiException exception,
                                                                  WebRequest request) {
        logError(request, exception.getInternalMessage(), exception.getLogLevel());
        var errorMessage = new ErrorMessage(exception.getCode(), exception.getUserMessage());
        return new ResponseEntity<>(errorMessage, exception.getStatus());
    }

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
