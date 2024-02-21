package com.example.examensprojekt.service;

import com.example.examensprojekt.model.TestQuestion;
import com.example.examensprojekt.repository.TestQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    public List<TestQuestion> getAllQuestions() {
        return testQuestionRepository.findAll();
    }

    public TestQuestion getQuestionById(Long id) {
        return testQuestionRepository.findById(id).orElse(null);
    }

    public List<TestQuestion> getQuestionsByCategory(Long categoryId) {
        return testQuestionRepository.findByCategoryId(categoryId);
    }

    public TestQuestion createQuestion(TestQuestion question) {
        return testQuestionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        testQuestionRepository.deleteById(id);
    }

    //TODO
    //Get a random question
    //Random question can be totally random or random from a category


}
