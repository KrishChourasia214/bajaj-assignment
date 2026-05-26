package com.bajaj;

import com.bajaj.dto.BfhlRequest;
import com.bajaj.dto.BfhlResponse;
import com.bajaj.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    // ── Example A ──────────────────────────────────────────────────────────────
    @Test
    void exampleA_oddNumbers() {
        BfhlResponse r = process("a", "1", "334", "4", "R", "$");
        assertEquals(List.of("1"), r.getOddNumbers());
    }

    @Test
    void exampleA_evenNumbers() {
        BfhlResponse r = process("a", "1", "334", "4", "R", "$");
        assertEquals(List.of("334", "4"), r.getEvenNumbers());
    }

    @Test
    void exampleA_alphabets() {
        BfhlResponse r = process("a", "1", "334", "4", "R", "$");
        assertEquals(List.of("A", "R"), r.getAlphabets());
    }

    @Test
    void exampleA_specialCharacters() {
        BfhlResponse r = process("a", "1", "334", "4", "R", "$");
        assertEquals(List.of("$"), r.getSpecialCharacters());
    }

    @Test
    void exampleA_sum() {
        BfhlResponse r = process("a", "1", "334", "4", "R", "$");
        assertEquals("339", r.getSum());
    }

    @Test
    void exampleA_concatString() {
        BfhlResponse r = process("a", "1", "334", "4", "R", "$");
        assertEquals("Ra", r.getConcatString());
    }

    // ── Example B ──────────────────────────────────────────────────────────────
    @Test
    void exampleB_oddNumbers() {
        BfhlResponse r = process("2", "a", "y", "4", "&", "-", "*", "5", "92", "b");
        assertEquals(List.of("5"), r.getOddNumbers());
    }

    @Test
    void exampleB_evenNumbers() {
        BfhlResponse r = process("2", "a", "y", "4", "&", "-", "*", "5", "92", "b");
        assertEquals(List.of("2", "4", "92"), r.getEvenNumbers());
    }

    @Test
    void exampleB_alphabets() {
        BfhlResponse r = process("2", "a", "y", "4", "&", "-", "*", "5", "92", "b");
        assertEquals(List.of("A", "Y", "B"), r.getAlphabets());
    }

    @Test
    void exampleB_specialCharacters() {
        BfhlResponse r = process("2", "a", "y", "4", "&", "-", "*", "5", "92", "b");
        assertEquals(List.of("&", "-", "*"), r.getSpecialCharacters());
    }

    @Test
    void exampleB_sum() {
        BfhlResponse r = process("2", "a", "y", "4", "&", "-", "*", "5", "92", "b");
        assertEquals("103", r.getSum());
    }

    @Test
    void exampleB_concatString() {
        BfhlResponse r = process("2", "a", "y", "4", "&", "-", "*", "5", "92", "b");
        assertEquals("ByA", r.getConcatString());
    }

    // ── Example C ──────────────────────────────────────────────────────────────
    @Test
    void exampleC_alphabets() {
        BfhlResponse r = process("A", "ABCD", "DOE");
        assertEquals(List.of("A", "ABCD", "DOE"), r.getAlphabets());
    }

    @Test
    void exampleC_emptyNumbers() {
        BfhlResponse r = process("A", "ABCD", "DOE");
        assertTrue(r.getOddNumbers().isEmpty());
        assertTrue(r.getEvenNumbers().isEmpty());
    }

    @Test
    void exampleC_sum() {
        BfhlResponse r = process("A", "ABCD", "DOE");
        assertEquals("0", r.getSum());
    }

    @Test
    void exampleC_concatString() {
        BfhlResponse r = process("A", "ABCD", "DOE");
        assertEquals("EoDdCbAa", r.getConcatString());
    }

    // ── Metadata ───────────────────────────────────────────────────────────────
    @Test
    void response_isSuccess_true() {
        BfhlResponse r = process("1");
        assertTrue(r.isSuccess());
    }

    @Test
    void response_userId_format() {
        BfhlResponse r = process("1");
        assertEquals("krish_chourasia_21102005", r.getUserId());
    }

    @Test
    void response_email() {
        BfhlResponse r = process("1");
        assertEquals("krishchourasia231303@acropolis.in", r.getEmail());
    }

    @Test
    void response_rollNumber() {
        BfhlResponse r = process("1");
        assertEquals("0827IT231069", r.getRollNumber());
    }

    // ── Edge cases ─────────────────────────────────────────────────────────────
    @Test
    void emptyData_returnsZeroSum() {
        BfhlResponse r = process();
        assertEquals("0", r.getSum());
        assertTrue(r.getConcatString().isEmpty());
    }

    @Test
    void zeroIsEven() {
        BfhlResponse r = process("0");
        assertTrue(r.getEvenNumbers().contains("0"));
        assertTrue(r.getOddNumbers().isEmpty());
    }

    // ── Helper ─────────────────────────────────────────────────────────────────
    private BfhlResponse process(String... items) {
        BfhlRequest req = new BfhlRequest();
        req.setData(Arrays.asList(items));
        return service.process(req);
    }
}
