package com.selflearning.englishcourses.repository;

import com.selflearning.englishcourses.domain.Lesson;
import com.selflearning.englishcourses.repository.jpa.CourseJpaRepository;
import com.selflearning.englishcourses.repository.jpa.LessonJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonJpaRepositoryTest {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private LessonJpaRepository lessonJpaRepository;

    @Test
    public void testCreateLesson(){
        Lesson lesson = new Lesson();
        lesson.setCourse(courseJpaRepository.findByName("Tiếng Anh giao tiếp 360").get());
        lesson.setOrderNumber(7);
        lessonJpaRepository.save(lesson);
    }
}
