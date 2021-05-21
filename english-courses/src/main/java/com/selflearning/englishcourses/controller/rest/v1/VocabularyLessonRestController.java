package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.service.VocabularyLessonService;
import com.selflearning.englishcourses.service.dto.VocabularyLessonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class VocabularyLessonRestController {

    private VocabularyLessonService vocabularyLessonService;

    @Autowired
    public void setVocabularyLessonService(VocabularyLessonService vocabularyLessonService) {
        this.vocabularyLessonService = vocabularyLessonService;
    }

    @PostMapping("/vocabularyLessons")
    public ResponseEntity<VocabularyLessonDto> createVocabularyLesson(@RequestBody VocabularyLessonDto vocabularyLessonDto){
        return null;
    }

}
