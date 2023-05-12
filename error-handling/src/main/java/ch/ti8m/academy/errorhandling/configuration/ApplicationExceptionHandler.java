package ch.ti8m.academy.errorhandling.configuration;

import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;
import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(CustomLockedException.class)
    public ErrorMessage handleLocked() {
        return new ErrorMessage(
                ErrorCode.RESOURCE_LOCKED,
                "The resource cannot be accesses"
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorMessage handleValidationException(ValidationException ex, WebRequest request) {
        logError(request, ex.getMessage(), LogLevel.ERROR);
        return new ErrorMessage(ErrorCode.MALFORMED_USER_REQUEST, "Not valid request,");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PersistenceException.class)
    public ErrorMessage handlePersistenceException(PersistenceException ex, WebRequest request) {
        logError(request, ex.getMessage(), LogLevel.ERROR);
        return new ErrorMessage(ErrorCode.EXECUTION_REQUEST, "Unable to finalyze request.");
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
