package com.example.examensprojekt.service;

import com.example.examensprojekt.model.TestQuestion;
import com.example.examensprojekt.repository.TestQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
    public TestQuestion getRandomQuestion() {
        List<TestQuestion> allQuestions = testQuestionRepository.findAll();
        if (!allQuestions.isEmpty()) {
            Random random = new Random();
            return allQuestions.get(random.nextInt(allQuestions.size()));
        }
        return null; // Return null if there are no questions in the database
    }

    public TestQuestion getRandomQuestionByCategory(Long categoryId) {
        List<TestQuestion> categoryQuestions = testQuestionRepository.findByCategoryId(categoryId);
        if (!categoryQuestions.isEmpty()) {
            Random random = new Random();
            return categoryQuestions.get(random.nextInt(categoryQuestions.size()));
        }
        return null; // Return null if there are no questions in the specified category
    }


}
