package ch.ti8m.academy.errorhandling.configuration;

import lombok.Value;

@Value
public class ErrorMessage {
    ErrorCode code;
    String error;
}
