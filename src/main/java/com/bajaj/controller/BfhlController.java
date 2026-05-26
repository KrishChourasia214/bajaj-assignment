package com.bajaj.controller;

import com.bajaj.dto.BfhlRequest;
import com.bajaj.dto.BfhlResponse;
import com.bajaj.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> process(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.process(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "bajaj-backend",
                "user_id", "krish_chourasia_21102005"
        ));
    }
}
