package com.selflearning.englishcourses.controller.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/management")
public class CourseManagementController {

    @GetMapping("/courses")
    public String coursePage(Model model){
        model.addAttribute("courseManagement", true);
        return "admin/categories/courses";
    }

    @GetMapping("/courses/page/{number}")
    public String coursePage(Model model, @PathVariable("number") Integer page) {
        return coursePage(model);
    }

    @GetMapping("/lessons")
    public String lessonPage(Model model){
        model.addAttribute("lessonManagement", true);
        return "admin/categories/lessons";
    }

    @GetMapping("/lessons/page/{number}")
    public String lessonPage(Model model, @PathVariable("number") Integer page) {
        return lessonPage(model);
    }

    @GetMapping("/modules")
    public String modulePage(Model model){
        model.addAttribute("moduleManagement", true);
        return "admin/categories/modules";
    }

    @GetMapping("/modules/page/{number}")
    public String modulePage(Model model, @PathVariable("number") Integer page) {
        return modulePage(model);
    }

    @ModelAttribute("courses")
    public Boolean activeCourseManagement(){
        return true;
    }

}
