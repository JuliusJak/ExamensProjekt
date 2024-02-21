package com.example.examensprojekt.service;

import com.example.examensprojekt.model.TestQuestion;
import com.example.examensprojekt.repository.TestQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public TestQuestion getRandomQuestion() {
        List<TestQuestion> allQuestions = testQuestionRepository.findAll();
        if (!allQuestions.isEmpty()) {
            Random random = new Random();
            return allQuestions.get(random.nextInt(allQuestions.size()));
        }
        return null;
    }

    public TestQuestion getRandomQuestionByCategory(Long categoryId) {
        List<TestQuestion> categoryQuestions = testQuestionRepository.findByCategoryId(categoryId);
        if (!categoryQuestions.isEmpty()) {
            Random random = new Random();
            return categoryQuestions.get(random.nextInt(categoryQuestions.size()));
        }
        return null;
    }

    public Set<Long> getQuestionsFromCategory(int amount, Long categoryId) {
        List<TestQuestion> categoryQuestions = testQuestionRepository.findByCategoryId(categoryId);
        Set<Long> selectedQuestionIds = new HashSet<>();

        if (!categoryQuestions.isEmpty()) {
            int totalQuestions = categoryQuestions.size();

            if (amount <= totalQuestions) {
                Random random = new Random();

                while (selectedQuestionIds.size() < amount) {
                    int randomIndex = random.nextInt(totalQuestions);
                    TestQuestion selectedQuestion = categoryQuestions.get(randomIndex);
                    selectedQuestionIds.add(selectedQuestion.getId());
                }
            }
        }

        return selectedQuestionIds;
    }

    public List<TestQuestion> getQuestionsByIds(Set<Long> questionIds) {
        return testQuestionRepository.findAllById(questionIds);
    }

}
