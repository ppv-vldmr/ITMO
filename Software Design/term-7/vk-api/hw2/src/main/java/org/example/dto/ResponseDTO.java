package org.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {
    private final SuccessDTO success;
    private final ErrorDTO error;

    @JsonCreator
    public ResponseDTO(
            @JsonProperty("response") SuccessDTO success,
            @JsonProperty("error") ErrorDTO error
    ) {
        this.success = success;
        this.error = error;
    }

    public SuccessDTO getSuccess() {
        return success;
    }

    public ErrorDTO getError() {
        return error;
    }
}
