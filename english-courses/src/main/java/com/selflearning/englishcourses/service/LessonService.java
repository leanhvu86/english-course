package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Lesson;
import com.selflearning.englishcourses.service.dto.LessonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface LessonService extends BaseCurdService<Lesson, UUID>, ModelMapperService<Lesson, LessonDto> {

    Optional<Lesson> findByLessonOrderNumberAndCourseId(Integer orderNumber, UUID courseId);

    Page<Lesson> findByCourseId(UUID courseId, Pageable pageable);

}
