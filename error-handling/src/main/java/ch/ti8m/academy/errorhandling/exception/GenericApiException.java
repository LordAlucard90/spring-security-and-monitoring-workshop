package ch.ti8m.academy.errorhandling.exception;

import ch.ti8m.academy.errorhandling.configuration.ErrorCode;
import ch.ti8m.academy.errorhandling.configuration.LogLevel;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;
import org.springframework.http.HttpStatus;

    @Value @With @EqualsAndHashCode(callSuper = true)
    public class GenericApiException extends RuntimeException {
        HttpStatus status;
        ErrorCode code;
        String userMessage;
        String internalMessage;
        LogLevel logLevel;

        public static GenericApiException failedDependency() {
            var message = "A required dependency is missing.";
            return new GenericApiException(
                    HttpStatus.FAILED_DEPENDENCY,
                    ErrorCode.MISSING_DEPENDENCY,
                    message,
                    message,
                    LogLevel.ERROR
            );
        }
    }
