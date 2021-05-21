package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Course;
import com.selflearning.englishcourses.domain.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LessonJpaRepository extends JpaRepository<Lesson, UUID> {

    Optional<Lesson> findByCourseAndOrderNumber(Course course, Integer orderNumber);

    @Query("SELECT l FROM Lesson l WHERE l.orderNumber = :orderNumber AND l.course.id = :courseId")
    Optional<Lesson> findByLessonOrderNumberAndCourseId(@Param("orderNumber") Integer orderNumber,@Param("courseId") UUID courseId);

    @Query("SELECT l FROM Lesson l WHERE l.course.id = :courseId")
    Page<Lesson> findByCourseId(@Param("courseId") UUID courseId, Pageable pageable);
}
