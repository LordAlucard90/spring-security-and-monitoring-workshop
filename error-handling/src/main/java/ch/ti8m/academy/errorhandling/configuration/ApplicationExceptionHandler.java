package ch.ti8m.academy.errorhandling.configuration;

import ch.ti8m.academy.errorhandling.exception.CustomLockedException;
import ch.ti8m.academy.errorhandling.exception.GenericApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
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
                "The resource cannot be accessed"
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
        return new ErrorMessage(ErrorCode.EXECUTION_ERROR, "Unable to finalize request.");
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

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers,
                                                                      HttpStatus status,
                                                                      WebRequest request) {
        var errorMessage = new ErrorMessage(ErrorCode.MALFORMED_USER_REQUEST, "Not Supported");
        return ResponseEntity.status(status).body(errorMessage);
    }
}
