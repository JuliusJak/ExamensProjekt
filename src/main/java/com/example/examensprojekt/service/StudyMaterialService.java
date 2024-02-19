package com.example.examensprojekt.service;

import com.example.examensprojekt.model.StudyMaterial;
import com.example.examensprojekt.repository.StudyMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyMaterialService {

    @Autowired
    private StudyMaterialRepository studyMaterialRepository;

    public List<StudyMaterial> getAllStudyMaterials() {
        return studyMaterialRepository.findAll();
    }

    public StudyMaterial getStudyMaterialById(Long id) {
        Optional<StudyMaterial> optionalStudyMaterial = studyMaterialRepository.findById(id);
        return optionalStudyMaterial.orElse(null);
    }

    public List<StudyMaterial> searchStudyMaterials(String content) {
        if (content != null && !content.isEmpty()) {
            return studyMaterialRepository.findByTitleContainingOrDescriptionContaining(content, content);
        } else {
            return getAllStudyMaterials();
        }
    }
}
