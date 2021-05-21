package com.selflearning.englishcourses.controller.web;

import com.selflearning.englishcourses.domain.*;
import com.selflearning.englishcourses.service.CourseService;
import com.selflearning.englishcourses.service.LessonModuleService;
import com.selflearning.englishcourses.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    private LessonService lessonService;

    private LessonModuleService lessonModuleService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Autowired
    public void setLessonModuleService(LessonModuleService lessonModuleService) {
        this.lessonModuleService = lessonModuleService;
    }

    @GetMapping("/{id}")
    public String getCourse(@PathVariable("id") UUID id, Model model) {
        Course course = courseService.get(id);
        List<Lesson> lessons = course.getLessons();
        lessons.sort(Comparator.comparing(Lesson::getOrderNumber));
        model.addAttribute("course", course);
        model.addAttribute("lessons", lessons);
        return "courses/course-detail";
    }

    @GetMapping("/{id}/lessons/{lessonId}")
    public String getLesson(@PathVariable("id") UUID courseId, @PathVariable("lessonId") UUID lessonId, Model model) {
        Lesson lesson = lessonService.get(lessonId);
        List<LessonModule> lessonModules = lesson.getLessonModules();
        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("lesson", lesson);
        model.addAttribute("lessonModules", lessonModules);
        return "courses/lessons";
    }

    @GetMapping("/{id}/lessons/{lessonId}/modules/{lessonModule}")
    public String getLessonDetail(@PathVariable("id") UUID courseId,
                                  @PathVariable("lessonId") UUID lessonId,
                                  @PathVariable("lessonModule") UUID lessonModuleId,
                                  Model model) {
        LessonModule lessonModule = lessonModuleService.get(lessonModuleId);
        Module module = lessonModule.getModule();
        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        switch (module.getName()) {
            case "Luyện từ vựng":
                VocabularyLesson vocabularyLesson = lessonModule.getVocabularyLesson();
                model.addAttribute("vocabularyLesson", vocabularyLesson);
                return "/courses/lessons/vocabulary";
            case "Luyện ngữ pháp":
                GrammarLesson grammarLesson = lessonModule.getGrammarLesson();
                model.addAttribute("grammarLesson", grammarLesson);
                return "courses/lessons/grammar";
            case "Luyện hội thoại":
                return "courses/lessons/conversation";
            default:
                return "errors/404";
        }
    }


}
