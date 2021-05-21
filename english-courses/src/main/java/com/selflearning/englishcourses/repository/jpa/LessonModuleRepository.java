package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.LessonModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonModuleRepository extends JpaRepository<LessonModule, UUID> {
}
