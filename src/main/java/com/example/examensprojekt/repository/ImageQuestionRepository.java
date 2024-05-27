package com.example.examensprojekt.repository;
import com.example.examensprojekt.model.ImageQuestion;
import com.example.examensprojekt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageQuestionRepository extends JpaRepository<ImageQuestion, Long> {

    @Query("SELECT iq FROM ImageQuestion iq WHERE (:id IS NULL OR iq.id = :id) " +
            "AND (:name IS NULL OR iq.name = :name) " +
            "AND (:question IS NULL OR iq.question = :question) ")
    List<ImageQuestion> findImageQuestionsWithParams(Long id, String name, String question);
}
