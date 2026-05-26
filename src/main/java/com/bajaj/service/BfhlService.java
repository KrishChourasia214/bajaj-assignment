package com.bajaj.service;

import com.bajaj.dto.BfhlRequest;
import com.bajaj.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse process(BfhlRequest request);
}
