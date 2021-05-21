package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Course;
import com.selflearning.englishcourses.service.dto.CourseDto;

import java.util.UUID;

public interface CourseService extends BaseCurdService<Course, UUID>,
        ModelMapperService<Course, CourseDto> {

}
