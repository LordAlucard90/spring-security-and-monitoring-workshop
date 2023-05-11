package ch.ti8m.academy.errorhandling.exception;

import ch.ti8m.academy.errorhandling.configuration.ErrorCode;
import ch.ti8m.academy.errorhandling.configuration.LogLevel;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;
import org.springframework.http.HttpStatus;

@Value
@With
@EqualsAndHashCode(callSuper = true)
public class GenericApiException extends RuntimeException {
    HttpStatus status;
    ErrorCode code;
    String userMessage;
    String internalMessage;
    LogLevel logLevel;

    public static GenericApiException notFound() {
        var defaultMessage = "Nothing found.";
        return new GenericApiException(
                HttpStatus.NOT_FOUND,
                ErrorCode.NOTING_HERE,
                defaultMessage,
                defaultMessage,
                LogLevel.ERROR
        );
    }
}
