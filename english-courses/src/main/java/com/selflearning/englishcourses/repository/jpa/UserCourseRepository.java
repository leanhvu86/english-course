package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourse, UUID> {
}
