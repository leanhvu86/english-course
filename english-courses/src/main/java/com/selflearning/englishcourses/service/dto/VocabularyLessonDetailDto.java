package com.selflearning.englishcourses.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class VocabularyLessonDetailDto extends BaseDto{

    private UUID sentenceId;
    private UUID vocabularyId;
    private UUID vocabularyLessonId;

}
