package br.com.raphaelmb.job_vacancy_manager_frontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FirstPageController {
    @GetMapping("/home")
    public String firstPage(Model model) {
        model.addAttribute("message", "home page");
        return "firstpage";
    }

    @PostMapping("/create")
    public String registerCandidate(Model model, Candidate candidate) {
        model.addAttribute("candidate", candidate);
        return "info";
    }

    private record Candidate(String email, String name) {}
}
