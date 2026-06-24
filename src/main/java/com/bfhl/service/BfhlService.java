package com.bfhl.service;

import com.bfhl.dto.BfhlRequestDto;
import com.bfhl.dto.BfhlResponseDto;

/**
 * Service interface for BFHL API processing logic.
 * Interface-based design as required by the problem statement.
 */
public interface BfhlService {

    /**
     * Processes the input data array and returns categorized results.
     *
     * @param requestDto the incoming request with data array
     * @return populated response DTO with all computed fields
     */
    BfhlResponseDto processData(BfhlRequestDto requestDto);
}
