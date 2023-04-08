package org.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDTO {
    private final int errorCode;
    private final String errorMessage;

    @JsonCreator
    public ErrorDTO(
            @JsonProperty("error_code") int errorCode,
            @JsonProperty("error_msg") String errorMessage
    ) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
