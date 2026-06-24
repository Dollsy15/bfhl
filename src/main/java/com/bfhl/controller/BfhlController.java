package com.bfhl.controller;

import com.bfhl.dto.BfhlRequestDto;
import com.bfhl.dto.BfhlResponseDto;
import com.bfhl.service.BfhlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller exposing the /bfhl endpoint.
 *
 * POST /bfhl  – Main processing endpoint
 * GET  /bfhl  – Returns operation code (optional health-check style endpoint)
 */
@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl
     * Processes input data array and returns categorized results.
     *
     * @param requestDto JSON body containing "data" array
     * @return 200 OK with BfhlResponseDto on success, 500 on error
     */
    @PostMapping
    public ResponseEntity<?> processData(@RequestBody BfhlRequestDto requestDto) {
        try {
            BfhlResponseDto response = bfhlService.processData(requestDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("is_success", false);
            errorBody.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }
    }

    /**
     * GET /bfhl
     * Returns a simple operation code — used to verify the endpoint is live.
     *
     * @return 200 OK with {"operation_code": 1}
     */
    @GetMapping
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        Map<String, Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }
}
