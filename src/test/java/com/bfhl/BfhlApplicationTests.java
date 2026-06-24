package com.bfhl;

import com.bfhl.dto.BfhlRequestDto;
import com.bfhl.dto.BfhlResponseDto;
import com.bfhl.service.BfhlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test cases verifying the BFHL API logic against all three examples
 * from the problem statement.
 */
@SpringBootTest
class BfhlApplicationTests {

    @Autowired
    private BfhlService bfhlService;

    /**
     * Example A: data = ["a", "1", "334", "4", "R", "$"]
     * Expected:
     *   odd_numbers    = ["1"]
     *   even_numbers   = ["334", "4"]
     *   alphabets      = ["A", "R"]
     *   special_chars  = ["$"]
     *   sum            = "339"
     *   concat_string  = "Ra"
     */
    @Test
    void testExampleA() {
        List<String> data = Arrays.asList("a", "1", "334", "4", "R", "$");
        BfhlRequestDto request = new BfhlRequestDto(data);
        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    /**
     * Example B: data = ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]
     * Expected:
     *   odd_numbers    = ["5"]
     *   even_numbers   = ["2", "4", "92"]
     *   alphabets      = ["A", "Y", "B"]
     *   special_chars  = ["&", "-", "*"]
     *   sum            = "103"
     *   concat_string  = "ByA"
     */
    @Test
    void testExampleB() {
        List<String> data = Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b");
        BfhlRequestDto request = new BfhlRequestDto(data);
        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    /**
     * Example C: data = ["A", "ABCD", "DOE"]
     * Expected:
     *   odd_numbers    = []
     *   even_numbers   = []
     *   alphabets      = ["A", "ABCD", "DOE"]
     *   special_chars  = []
     *   sum            = "0"
     *   concat_string  = "EoDdCbAa"
     */
    @Test
    void testExampleC() {
        List<String> data = Arrays.asList("A", "ABCD", "DOE");
        BfhlRequestDto request = new BfhlRequestDto(data);
        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of(), response.getOddNumbers());
        assertEquals(List.of(), response.getEvenNumbers());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertEquals(List.of(), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    /**
     * Edge case: empty data array
     */
    @Test
    void testEmptyData() {
        List<String> data = List.of();
        BfhlRequestDto request = new BfhlRequestDto(data);
        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of(), response.getOddNumbers());
        assertEquals(List.of(), response.getEvenNumbers());
        assertEquals(List.of(), response.getAlphabets());
        assertEquals(List.of(), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }
}
