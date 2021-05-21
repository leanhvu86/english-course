package com.selflearning.englishcourses.repository;

import com.selflearning.englishcourses.domain.Course;
import com.selflearning.englishcourses.domain.Lesson;
import com.selflearning.englishcourses.domain.LessonModule;
import com.selflearning.englishcourses.domain.Module;
import com.selflearning.englishcourses.repository.jpa.CourseJpaRepository;
import com.selflearning.englishcourses.repository.jpa.LessonJpaRepository;
import com.selflearning.englishcourses.repository.jpa.LessonModuleJpaRepository;
import com.selflearning.englishcourses.repository.jpa.ModuleJpaRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonModuleJpaRepositoryTest {

    private static final String COURSE_NAME = "Tiếng Anh giao tiếp 360";
    private static final String MODULE_NAME = "tesetaaasdfsd";

    @Autowired
    private LessonModuleJpaRepository lessonModuleJpaRepository;

    @Autowired
    private LessonJpaRepository lessonJpaRepository;

    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Test
    public void createLessonModule() {
        LessonModule lessonModule = new LessonModule();
        Course course = courseJpaRepository.findByName(COURSE_NAME).orElse(new Course(COURSE_NAME));
        Module module = moduleJpaRepository.findByName(MODULE_NAME).orElse(new Module(MODULE_NAME));
        Lesson lesson = lessonJpaRepository.findByCourseAndOrderNumber(course, 7).orElseGet(()->{
            Lesson newLesson = new Lesson();
            newLesson.setOrderNumber(7);
            newLesson.setCourse(course);
            return newLesson;
        });
        lessonModule.setModule(module);
        lessonModule.setLesson(lesson);
        lessonModuleJpaRepository.save(lessonModule);
        Assert.assertNotNull(lessonModule.getId());
    }

    @Test
    public void deleteAll(){
        lessonModuleJpaRepository.deleteAll();
    }

}
