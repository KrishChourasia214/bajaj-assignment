package com.bajaj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BfhlResponse {

    @JsonProperty("is_success")
    private boolean success;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    @JsonProperty("alphabets")
    private List<String> alphabets;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    @JsonProperty("sum")
    private String sum;

    @JsonProperty("concat_string")
    private String concatString;

    private BfhlResponse() {}

    public static Builder builder() {
        return new Builder();
    }

    public boolean isSuccess() { return success; }
    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRollNumber() { return rollNumber; }
    public List<String> getOddNumbers() { return oddNumbers; }
    public List<String> getEvenNumbers() { return evenNumbers; }
    public List<String> getAlphabets() { return alphabets; }
    public List<String> getSpecialCharacters() { return specialCharacters; }
    public String getSum() { return sum; }
    public String getConcatString() { return concatString; }

    public static class Builder {
        private final BfhlResponse response = new BfhlResponse();

        public Builder isSuccess(boolean val) { response.success = val; return this; }
        public Builder userId(String val) { response.userId = val; return this; }
        public Builder email(String val) { response.email = val; return this; }
        public Builder rollNumber(String val) { response.rollNumber = val; return this; }
        public Builder oddNumbers(List<String> val) { response.oddNumbers = val; return this; }
        public Builder evenNumbers(List<String> val) { response.evenNumbers = val; return this; }
        public Builder alphabets(List<String> val) { response.alphabets = val; return this; }
        public Builder specialCharacters(List<String> val) { response.specialCharacters = val; return this; }
        public Builder sum(String val) { response.sum = val; return this; }
        public Builder concatString(String val) { response.concatString = val; return this; }

        public BfhlResponse build() { return response; }
    }
}
