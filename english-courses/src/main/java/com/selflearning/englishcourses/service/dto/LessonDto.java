package com.selflearning.englishcourses.service.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class LessonDto extends BaseDto{

    private Integer orderNumber;
    private UUID courseId;
    private String courseName;
    private List<LessonModuleDto> lessonModules;

}
