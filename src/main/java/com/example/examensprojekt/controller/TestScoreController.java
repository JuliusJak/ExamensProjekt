package com.example.examensprojekt.controller;

import com.example.examensprojekt.model.TestQuestion;
import com.example.examensprojekt.model.TestScore;
import com.example.examensprojekt.repository.TestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8501")
@RestController
@RequestMapping("/test")
public class TestScoreController {

    @Autowired
    private TestScoreRepository testScoreRepository;

    @PostMapping("/add")
    public ResponseEntity<TestScore> createQuestion(@RequestBody TestScore testScore) {

        TestScore savedTestScore = testScoreRepository.save(testScore);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestScore);
    }

    @GetMapping("/testScores")
    public ResponseEntity<List<TestScore>> getTestScoresByUsername(@RequestParam String username) {
        List<TestScore> testScores = testScoreRepository.findByUsername(username);
        if (testScores.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no test scores found
        } else {
            return ResponseEntity.ok(testScores); // Return the test scores if found
        }
    }
}
