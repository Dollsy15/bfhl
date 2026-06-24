package com.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Request DTO for POST /bfhl endpoint.
 * Accepts a list of strings (can contain numbers, alphabets, special characters).
 */
public class BfhlRequestDto {

    @JsonProperty("data")
    private List<String> data;

    public BfhlRequestDto() {}

    public BfhlRequestDto(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
