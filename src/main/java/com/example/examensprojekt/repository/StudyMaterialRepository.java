package com.example.examensprojekt.repository;

import com.example.examensprojekt.model.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long> {
    List<StudyMaterial> findByTitleContainingOrDescriptionContaining(String title, String description);
}
