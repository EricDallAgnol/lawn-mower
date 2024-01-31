package com.lawn.controller;

import com.lawn.service.LawnMowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LawnMowerController {

    @Autowired
    LawnMowerService lawnMowerService;

    @PostMapping("/instructions")
    public ResponseEntity<String> runSimulation(@RequestBody String instructions) {
        try {
            String response = lawnMowerService.runSimulation(instructions);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
