package com.selflearning.englishcourses.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class VocabularyLessonDto extends BaseDto{

    private String lessonModuleId;
    private String vocabularyLessonId;
    private List<VocabularyLessonDetailDto> vocabularyLessonDetail;

}
