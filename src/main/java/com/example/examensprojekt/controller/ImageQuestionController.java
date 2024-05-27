package com.example.examensprojekt.controller;

import com.example.examensprojekt.model.ImageQuestion;
import com.example.examensprojekt.model.User;
import com.example.examensprojekt.repository.ImageQuestionRepository;
import com.example.examensprojekt.service.ImageQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8501")
@RestController
@RequestMapping("/image-questions")
public class ImageQuestionController {

    private final ImageQuestionService imageQuestionService;

    @Autowired
    public ImageQuestionController(ImageQuestionService imageQuestionService) {
        this.imageQuestionService = imageQuestionService;
    }

    @Autowired
    public ImageQuestionRepository imageQuestionRepository;

    @PostMapping
    public ResponseEntity<ImageQuestion> saveImageQuestion(@RequestBody ImageQuestion imageQuestion) {
        ImageQuestion savedImageQuestion = imageQuestionService.saveImageQuestion(imageQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedImageQuestion);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ImageQuestion>> searchImageQuestions(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String question) {

        List<ImageQuestion> imageQuestions = imageQuestionRepository.findImageQuestionsWithParams(
                id, name, question);

        if (imageQuestions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(imageQuestions);
        }
    }
}
