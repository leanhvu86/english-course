package com.selflearning.englishcourses.controller.web.admin;

import com.selflearning.englishcourses.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    private DashboardService dashboardService;

    @Autowired
    public void setDashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalSentences", dashboardService.getTotalSentences());
        model.addAttribute("totalPhrases", dashboardService.getTotalPhrases());
        model.addAttribute("totalVocabularies", dashboardService.getTotalVocabularies());
        model.addAttribute("totalUsers", dashboardService.getTotalUsers());
        return "admin/dashboard";
    }

    @GetMapping
    public String admin() {
        return "redirect:/admin/dashboard";
    }

}
