package ch.ti8m.academy.errorhandling.configuration;

import ch.ti8m.academy.errorhandling.exception.CustomTooEarlyException;
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

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.TOO_EARLY)
    @ExceptionHandler(CustomTooEarlyException.class)
    public ErrorMessage handleCustomTooEarlyException(CustomTooEarlyException exception) {
        return new ErrorMessage(ErrorCode.NOT_YET_AVAILABLE, exception.getMessage());
    }

    @ExceptionHandler(GenericApiException.class)
    public ResponseEntity<ErrorMessage> handleGenericApiException(GenericApiException exception,
                                                                  WebRequest request) {
        logError(request, exception.getInternalMessage(), exception.getLogLevel());
        var errorMessage = new ErrorMessage(exception.getCode(), exception.getUserMessage());
        return new ResponseEntity<>(errorMessage, exception.getStatus());
    }
    private void logError(WebRequest request, String internalMessage, LogLevel logLevel) {
        var message = String.format("Unaboe to respond to user '%s' at %s due to error '%s'",
                request.getRemoteUser(), request.getDescription(false), internalMessage);
        switch (logLevel) { case INFO: log.info(message); break;
                            case WARN: log.warn(message); break;
                            default: log.error(message); }
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.warn(
                "Received request at {} for media type {} from user {}!",
                request.getDescription(false),
                request.getHeader(HttpHeaders.ACCEPT),
                request.getRemoteUser()
        );
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, HttpStatus.BAD_REQUEST, request);
    }
}
