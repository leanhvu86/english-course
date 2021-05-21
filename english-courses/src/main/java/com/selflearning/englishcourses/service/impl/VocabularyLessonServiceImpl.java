package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.VocabularyLesson;
import com.selflearning.englishcourses.repository.jpa.VocbularyLessonJpaRepository;
import com.selflearning.englishcourses.service.VocabularyLessonService;
import com.selflearning.englishcourses.service.dto.VocabularyLessonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VocabularyLessonServiceImpl implements VocabularyLessonService {

    private VocbularyLessonJpaRepository vocbularyLessonJpaRepository;

    @Autowired
    public void setVocbularyLessonJpaRepository(VocbularyLessonJpaRepository vocbularyLessonJpaRepository) {
        this.vocbularyLessonJpaRepository = vocbularyLessonJpaRepository;
    }

    @Override
    public VocabularyLesson get(UUID id) {
        return vocbularyLessonJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(VocabularyLesson vocabularyLesson) {
        vocbularyLessonJpaRepository.save(vocabularyLesson);
    }

    @Override
    public Page<VocabularyLesson> findAll(Pageable pageable) {
        return vocbularyLessonJpaRepository.findAll(pageable);
    }

    @Override
    public VocabularyLesson convertDtoToEntity(VocabularyLessonDto vocabularyLessonDto) {
        return null;
    }

    @Override
    public List<VocabularyLesson> convertDtoToEntity(List<VocabularyLessonDto> vocabularyLessonDtos) {
        return null;
    }

    @Override
    public VocabularyLessonDto convertEntityToDto(VocabularyLesson entity) {
        return null;
    }

    @Override
    public List<VocabularyLessonDto> convertEntityToDto(List<VocabularyLesson> entityList) {
        return null;
    }

    @Override
    public Page<VocabularyLessonDto> convertEntityPageToDtoPage(Page<VocabularyLesson> page) {
        return null;
    }
}
