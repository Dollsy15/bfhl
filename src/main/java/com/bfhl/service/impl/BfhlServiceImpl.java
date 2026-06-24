package com.bfhl.service.impl;

import com.bfhl.dto.BfhlRequestDto;
import com.bfhl.dto.BfhlResponseDto;
import com.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BfhlService that processes input data and categorizes each element.
 *
 * Logic:
 * - Numbers (possibly multi-digit) are split into even and odd lists.
 * - Alphabets are converted to uppercase.
 * - Any element that is not a pure integer and not a pure alphabet string is a special character.
 * - sum = sum of all numeric values in the array.
 * - concat_string = all alphabetical characters reversed, then alternating caps starting
 *   from the LAST character as uppercase (i.e., last char = upper, second-to-last = lower, etc.)
 */
@Service
public class BfhlServiceImpl implements BfhlService {

    // ---- User constants (replace with your actual details) ----
    private static final String FULL_NAME = "dollsy_rani";   // lowercase, underscore-separated
    private static final String DOB       = "15052005";    // ddmmyyyy
    private static final String EMAIL     = "dollsy0245.be23@chitkara.edu.in";
    private static final String ROLL_NO   = "2310990245";

    @Override
    public BfhlResponseDto processData(BfhlRequestDto requestDto) {

        List<String> data = requestDto.getData();

        List<String> evenNumbers      = new ArrayList<>();
        List<String> oddNumbers       = new ArrayList<>();
        List<String> alphabets        = new ArrayList<>();
        List<String> specialChars     = new ArrayList<>();
        long         numericalSum     = 0;
        // collect all individual alphabet characters (expanded from multi-char strings)
        StringBuilder alphaCollector  = new StringBuilder();

        for (String item : data) {
            if (item == null || item.isEmpty()) continue;

            if (isInteger(item)) {
                // ---- Number ----
                long value = Long.parseLong(item);
                numericalSum += value;
                if (value % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }

            } else if (isAlphaString(item)) {
                // ---- Pure alphabetical string ----
                alphabets.add(item.toUpperCase());
                // collect each letter for concat_string
                for (char c : item.toCharArray()) {
                    alphaCollector.append(c);
                }

            } else {
                // ---- Special character (anything else) ----
                specialChars.add(item);
            }
        }

        // Build concat_string: reverse the collected alphabets then apply alternating caps
        // Rule: last character (index 0 of original) → uppercase, next → lowercase, etc.
        String concatString = buildConcatString(alphaCollector.toString());

        // Populate response
        BfhlResponseDto response = new BfhlResponseDto();
        response.setSuccess(true);
        response.setUserId(FULL_NAME + "_" + DOB);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NO);
        response.setEvenNumbers(evenNumbers);
        response.setOddNumbers(oddNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialChars);
        response.setSum(String.valueOf(numericalSum));
        response.setConcatString(concatString);

        return response;
    }

    // ---------------------------------------------------------------
    // Helper: check if a string represents a valid integer
    // (handles negatives too, e.g. "-5")
    // ---------------------------------------------------------------
    private boolean isInteger(String s) {
        if (s == null || s.isEmpty()) return false;
        int start = 0;
        if (s.charAt(0) == '-') {
            if (s.length() == 1) return false;
            start = 1;
        }
        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    // ---------------------------------------------------------------
    // Helper: check if every character in the string is alphabetic
    // ---------------------------------------------------------------
    private boolean isAlphaString(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    // ---------------------------------------------------------------
    // Build concat_string:
    // 1. Collect all individual alphabet chars from input (in order).
    // 2. Reverse the collected sequence.
    // 3. Apply alternating caps: index 0 → Upper, index 1 → lower, ...
    //
    // Example A: input alphabets = ["a","R"]  → collected="aR" → reversed="Ra"
    //            → alternating caps → "Ra" (R upper, a lower) → "Ra"  ✓
    //
    // Example B: input alphabets = ["a","y","b"] → collected="ayb" → reversed="bya"
    //            → "Bya" → but expected is "ByA" ...
    //            Wait, let me re-check with Example B:
    //            data = ["2","a","y","4","&","-","*","5","92","b"]
    //            alpha chars in order: a, y, b  → reversed: b, y, a
    //            alternating (0=upper): B, y, A → "ByA"  ✓
    //
    // Example C: alphabets = ["A","ABCD","DOE"]
    //            chars in order: A,A,B,C,D,D,O,E → reversed: E,O,D,D,C,B,A,A
    //            alternating: E,o,D,d,C,b,A,a → "EoDdCbAa" ✓
    // ---------------------------------------------------------------
    private String buildConcatString(String collected) {
        // Step 1: reverse the collected character sequence
        String reversed = new StringBuilder(collected).reverse().toString();

        // Step 2: alternating caps (index 0 = uppercase)
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
