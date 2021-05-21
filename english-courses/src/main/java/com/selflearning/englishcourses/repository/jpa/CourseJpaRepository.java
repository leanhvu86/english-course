package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseJpaRepository extends JpaRepository<Course, UUID> {

    Optional<Course> findByName(String name);

}
