package com.example.examensprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.examensprojekt.model.StudyMaterial;
import com.example.examensprojekt.service.StudyMaterialService;

import java.util.List;

@RestController
@RequestMapping("/api/study")
public class StudyMaterialController {

    @Autowired
    private StudyMaterialService studyMaterialService;

    @GetMapping("/study-materials")
    public ResponseEntity<?> getAllStudyMaterials() {
        List<StudyMaterial> studyMaterials = studyMaterialService.getAllStudyMaterials();

        if (studyMaterials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No study materials found.");
        } else {
            return ResponseEntity.ok(studyMaterials);
        }
    }

    @GetMapping("/study-materials/{id}")
    public ResponseEntity<?> getStudyMaterialById(@PathVariable Long id) {
        StudyMaterial studyMaterial = studyMaterialService.getStudyMaterialById(id);

        if (studyMaterial != null) {
            return ResponseEntity.ok(studyMaterial);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Study material not found for ID: " + id);
        }
    }

    @GetMapping("/study-materials/search")
    public ResponseEntity<?> searchStudyMaterials(@RequestParam(required = false) String content) {
        List<StudyMaterial> searchResults = studyMaterialService.searchStudyMaterials(content);

        if (searchResults.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No study materials found matching the search criteria.");
        } else {
            return ResponseEntity.ok(searchResults);
        }
    }
}