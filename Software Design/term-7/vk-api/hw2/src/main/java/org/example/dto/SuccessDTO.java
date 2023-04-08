package org.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessDTO {
    private final int count;
    private final int totalCount;

    @JsonCreator
    public SuccessDTO(
            @JsonProperty("count") int count,
            @JsonProperty("total_count") int totalCount
    ) {
        this.count = count;
        this.totalCount = totalCount;
    }

    public int getCount() {
        return count;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
