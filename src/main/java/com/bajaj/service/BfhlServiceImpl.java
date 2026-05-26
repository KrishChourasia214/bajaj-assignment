package com.bajaj.service;

import com.bajaj.dto.BfhlRequest;
import com.bajaj.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    // ── User constants ─────────────────────────────────────────────────────────
    private static final String USER_ID     = "krish_chourasia_21102005";
    private static final String EMAIL       = "krishchourasia231303@acropolis.in";
    private static final String ROLL_NUMBER = "0827IT231069";

    @Override
    public BfhlResponse process(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers       = new ArrayList<>();
        List<String> evenNumbers      = new ArrayList<>();
        List<String> alphabets        = new ArrayList<>();
        List<String> specialChars     = new ArrayList<>();
        long         numericSum       = 0;
        // Collect all alphabetical characters (from every element) in order
        StringBuilder allAlphaChars   = new StringBuilder();

        for (String item : data) {
            if (isNumeric(item)) {
                long value = Long.parseLong(item);
                numericSum += value;
                if (value % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isPureAlpha(item)) {
                // Entire token is alphabetical — uppercase it and add to alphabets list
                alphabets.add(item.toUpperCase());
                // Collect individual chars for concat_string
                for (char c : item.toCharArray()) {
                    allAlphaChars.append(c);
                }
            } else if (isSingleSpecial(item)) {
                specialChars.add(item);
            } else {
                // Mixed token: classify each character individually
                for (char c : item.toCharArray()) {
                    String s = String.valueOf(c);
                    if (Character.isLetter(c)) {
                        allAlphaChars.append(c);
                    } else if (Character.isDigit(c)) {
                        // digits inside mixed tokens don't go to number arrays
                        // but we still need to decide — per spec examples all items
                        // are single-element strings, so this branch is a safety net
                    } else {
                        specialChars.add(s);
                    }
                }
            }
        }

        String concatString = buildConcatString(allAlphaChars.toString());

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialChars)
                .sum(String.valueOf(numericSum))
                .concatString(concatString)
                .build();
    }

    // ── Helpers ────────────────────────────────────────────────────────────────

    /** True if the entire string represents an integer (possibly negative). */
    private boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        int start = s.charAt(0) == '-' ? 1 : 0;
        if (start == s.length()) return false;
        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    /** True if every character in the string is a letter. */
    private boolean isPureAlpha(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /** True if the string is a single non-alphanumeric character. */
    private boolean isSingleSpecial(String s) {
        return s != null && s.length() == 1
                && !Character.isLetterOrDigit(s.charAt(0));
    }

    /**
     * Builds the concat_string:
     *  - Take all alphabetical characters collected in order from the input.
     *  - Reverse that sequence.
     *  - Apply alternating caps: index 0 → uppercase, index 1 → lowercase, …
     *
     * Example A: input chars = ['a','R'] → reversed = ['R','a'] → "Ra"
     * Example B: input chars = ['a','y','b'] → reversed = ['b','y','a'] → "ByA"
     * Example C: input chars = ['A','A','B','C','D','D','O','E'] → reversed = ['E','O','D','D','C','B','A','A']
     *            → alternating caps → "EoDdCbAa"
     */
    private String buildConcatString(String allAlpha) {
        if (allAlpha.isEmpty()) return "";

        // Reverse
        String reversed = new StringBuilder(allAlpha).reverse().toString();

        // Alternating caps
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            result.append(i % 2 == 0
                    ? Character.toUpperCase(c)
                    : Character.toLowerCase(c));
        }
        return result.toString();
    }
}
