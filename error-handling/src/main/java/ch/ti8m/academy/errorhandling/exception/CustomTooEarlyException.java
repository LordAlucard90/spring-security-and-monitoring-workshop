package ch.ti8m.academy.errorhandling.exception;

public class CustomTooEarlyException extends RuntimeException {
    public CustomTooEarlyException(String message) {
        super(message);
    }
}
