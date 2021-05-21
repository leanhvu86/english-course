package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Lesson;
import com.selflearning.englishcourses.domain.LessonModule;
import com.selflearning.englishcourses.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LessonModuleJpaRepository extends JpaRepository<LessonModule, UUID> {

    Optional<LessonModule> findByLessonAndModule(Lesson lesson, Module module);

}
