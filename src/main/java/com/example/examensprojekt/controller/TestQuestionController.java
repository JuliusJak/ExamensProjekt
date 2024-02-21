package com.example.examensprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.examensprojekt.model.TestQuestion;
import com.example.examensprojekt.service.TestQuestionService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestQuestionController {

    @Autowired
    private TestQuestionService testQuestionService;

    @GetMapping("/questions")
    public ResponseEntity<List<TestQuestion>> getQuestions(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "categoryId", required = false) Long categoryId) {

        if (id != null) {
            TestQuestion question = testQuestionService.getQuestionById(id);
            if (question != null) {
                return ResponseEntity.ok(Collections.singletonList(question));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if (categoryId != null) {
            List<TestQuestion> questions = testQuestionService.getQuestionsByCategory(categoryId);
            return ResponseEntity.ok(questions);
        } else {
            List<TestQuestion> allQuestions = testQuestionService.getAllQuestions();
            return ResponseEntity.ok(allQuestions);
        }
    }

    @PostMapping("/questions/create")
    public ResponseEntity<TestQuestion> createQuestion(@RequestBody TestQuestion question) {

        TestQuestion createdQuestion = testQuestionService.createQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        testQuestionService.deleteQuestion(id);
        return ResponseEntity.ok("Question with ID " + id + " has been successfully deleted");
    }

}
