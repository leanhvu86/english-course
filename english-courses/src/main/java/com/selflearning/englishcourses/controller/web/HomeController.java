package com.selflearning.englishcourses.controller.web;

import com.selflearning.englishcourses.domain.Course;
import com.selflearning.englishcourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Course> courses = courseService.findAll(PageRequest.of(0, 10)).getContent();
        model.addAttribute("courses", courses);
        return "index";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/";
    }

}
