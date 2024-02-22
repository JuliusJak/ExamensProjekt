package com.example.examensprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.examensprojekt.model.TestQuestion;
import com.example.examensprojekt.service.TestQuestionService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/questions/random")
    public ResponseEntity<TestQuestion> getRandomQuestion(@RequestParam(required = false) Long categoryId) {
        TestQuestion randomQuestion;

        if (categoryId != null) {
            randomQuestion = testQuestionService.getRandomQuestionByCategory(categoryId);
        } else {
            randomQuestion = testQuestionService.getRandomQuestion();
        }

        if (randomQuestion != null) {
            return ResponseEntity.ok(randomQuestion);
        } else {
            return ResponseEntity.notFound().build();
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

    @GetMapping("/questions/randomFromCategory")
    public ResponseEntity<List<TestQuestion>> getRandomQuestionsFromCategory(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = true) int amount) {

        Set<Long> selectedQuestionIds = testQuestionService.getQuestionsFromCategory(amount, categoryId);
        List<TestQuestion> selectedQuestions = testQuestionService.getQuestionsByIds(selectedQuestionIds);

        if (!selectedQuestions.isEmpty()) {
            return ResponseEntity.ok(selectedQuestions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/questions/randomFromSelectedCategories")
    public ResponseEntity<List<TestQuestion>> getRandomQuestionsFromSelectedCategories(
            @RequestParam(required = true) int amount,
            @RequestParam(required = true) boolean categoryOne,
            @RequestParam(required = true) boolean categoryTwo,
            @RequestParam(required = true) boolean categoryThree) {

        Set<Long> selectedQuestionIds = new HashSet<>();

        if (categoryOne) {
            selectedQuestionIds.addAll(testQuestionService.getQuestionsFromCategory(amount, 1L));
        }
        if (categoryTwo) {
            selectedQuestionIds.addAll(testQuestionService.getQuestionsFromCategory(amount, 2L));
        }
        if (categoryThree) {
            selectedQuestionIds.addAll(testQuestionService.getQuestionsFromCategory(amount, 3L));
        }

        List<TestQuestion> selectedQuestions = testQuestionService.getQuestionsByIds(selectedQuestionIds);

        if (!selectedQuestions.isEmpty()) {
            return ResponseEntity.ok(selectedQuestions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
