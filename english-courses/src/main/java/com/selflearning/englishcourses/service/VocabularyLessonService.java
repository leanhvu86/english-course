package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.VocabularyLesson;
import com.selflearning.englishcourses.service.dto.VocabularyLessonDto;

import java.util.UUID;

public interface VocabularyLessonService extends BaseCurdService<VocabularyLesson, UUID>,
        ModelMapperService<VocabularyLesson, VocabularyLessonDto> {

}
