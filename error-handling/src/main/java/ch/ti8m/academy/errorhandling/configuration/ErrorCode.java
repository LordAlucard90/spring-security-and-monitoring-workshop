package ch.ti8m.academy.errorhandling.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ErrorCode {
    RESOURCE_LOCKED,
    NOT_YET_AVAILABLE,
    MALFORMED_USER_REQUEST,
    EXECUTION_REQUEST,
    NOTING_HERE,
}