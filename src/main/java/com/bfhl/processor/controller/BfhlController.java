package com.bfhl.processor.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    // Hardcoded User Information
    private static final String USER_ID = "john_doe_17091999";
    private static final String EMAIL = "john@xyz.com";
    private static final String ROLL_NUMBER = "ABCD123";

    @PostMapping
    public ResponseEntity<Map<String, Object>> handlePostRequest(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        // Extracting data from request
        List<String> data = (List<String>) request.get("data");

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        String highestLowercaseAlphabet = "";

        for (String item : data) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
                if (Character.isLowerCase(item.charAt(0)) && item.compareTo(highestLowercaseAlphabet) > 0) {
                    highestLowercaseAlphabet = item;
                }
            }
        }

        response.put("is_success", true);
        response.put("user_id", USER_ID);
        response.put("email", EMAIL);
        response.put("roll_number", ROLL_NUMBER);
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_lowercase_alphabet", Collections.singletonList(highestLowercaseAlphabet));

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Integer>> handleGetRequest() {
        Map<String, Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }
}