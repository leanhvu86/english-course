package com.selflearning.englishcourses.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LessonModuleDto extends BaseDto{

    private UUID lessonId;
    private UUID courseId;
    private Integer orderNumber;
    private UUID moduleId;
    private String moduleName;

}
