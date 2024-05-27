package com.example.examensprojekt.service;

import com.example.examensprojekt.model.ImageQuestion;
import com.example.examensprojekt.repository.ImageQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageQuestionService {

    private final ImageQuestionRepository imageQuestionRepository;

    @Autowired
    public ImageQuestionService(ImageQuestionRepository imageQuestionRepository) {
        this.imageQuestionRepository = imageQuestionRepository;
    }

    public ImageQuestion saveImageQuestion(ImageQuestion imageQuestion) {
        return imageQuestionRepository.save(imageQuestion);
    }

    public Optional<ImageQuestion> getImageQuestionById(Long id) {
        return imageQuestionRepository.findById(id);
    }

}
