package com.selflearning.englishcourses.controller.web.admin;

import com.selflearning.englishcourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/management")
public class CategoryManagementController {

    @GetMapping("/sentences")
    public String sentencePage(Model model) {
        model.addAttribute("sentenceManagement", true);
        return "admin/categories/sentences";
    }

    @GetMapping("/sentences/page/{number}")
    public String sentencePage(Model model, @PathVariable("number") Integer number) {
        return sentencePage(model);
    }

    @GetMapping("/vocabularies")
    public String vocabularyPage(Model model) {
        model.addAttribute("vocabularyManagement", true);
        return "admin/categories/vocabularies";
    }

    @GetMapping("/vocabularies/page/{number}")
    public String vocabularyPage(Model model, @PathVariable("number") Integer page) {
        return vocabularyPage(model);
    }

    @GetMapping("/phrases")
    public String phrasePage(Model model) {
        model.addAttribute("phraseManagement", true);
        return "admin/categories/phrases";
    }

    @GetMapping("/phrases/page/{number}/")
    public String phrasePage(Model model, @PathVariable("number") Integer page) {
        return phrasePage(model);
    }


    @ModelAttribute("categories")
    public Boolean setActive() {
        return true;
    }

}
