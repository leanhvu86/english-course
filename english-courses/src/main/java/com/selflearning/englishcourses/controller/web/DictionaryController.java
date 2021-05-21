package com.selflearning.englishcourses.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DictionaryController {

    @GetMapping("/dictionary")
    public String dictionaryPage(){
        return "dictionary";
    }

}
