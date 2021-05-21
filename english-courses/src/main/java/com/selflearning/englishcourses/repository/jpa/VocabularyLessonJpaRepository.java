package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.VocabularyLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VocabularyLessonJpaRepository extends JpaRepository<VocabularyLesson, UUID> {

    VocabularyLesson findByLessonModuleId(UUID lessonModuleId);

}
