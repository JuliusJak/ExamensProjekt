package com.example.examensprojekt.repository;

import com.example.examensprojekt.model.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {

    List<TestQuestion> findAll();

    Optional<TestQuestion> findById(Long id);

    List<TestQuestion> findByCategoryId(Long categoryId);
}
