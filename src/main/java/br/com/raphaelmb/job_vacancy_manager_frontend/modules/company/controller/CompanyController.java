package br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.controller;

import br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.dto.CreateCompanyDTO;
import br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.dto.CreateJobDTO;
import br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.service.CreateCompanyService;
import br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.service.CreateJobService;
import br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.service.ListAllJobsCompanyService;
import br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.service.LoginCompanyService;
import br.com.raphaelmb.job_vacancy_manager_frontend.utils.FormatErrorMessage;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyService createCompanyService;

    @Autowired
    private LoginCompanyService loginCompanyService;

    @Autowired
    private CreateJobService createJobService;

    @Autowired
    private ListAllJobsCompanyService listAllJobsCompanyService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("company", new CreateCompanyDTO());
        return "company/create";
    }

    @PostMapping("/create")
    public String save(Model model, CreateCompanyDTO createCompanyDTO) {
        try {
            this.createCompanyService.execute(createCompanyDTO);
            model.addAttribute("company", new CreateCompanyDTO());
        } catch (HttpClientErrorException e) {
            model.addAttribute("error_message", FormatErrorMessage.formatErrorMessage(e.getResponseBodyAsString()));
            model.addAttribute("company", createCompanyDTO);
        }

        return "company/create";
    }

    @GetMapping("/login")
    public String login() {
        return "company/login";
    }

    @PostMapping("/signIn")
    public String signIn(RedirectAttributes redirectAttributes, HttpSession session,String username, String password) {
        try {
            var token = this.loginCompanyService.execute(username, password);
            var grants = token.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase())).toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);
            auth.setDetails(token.getAccess_token());

            SecurityContextHolder.getContext().setAuthentication(auth);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            session.setAttribute("token", token);

            return "redirect:/company/jobs";
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("error_message", "Incorrect username/password");
            return "redirect:/company/login";
        }
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String jobs(Model model) {
        model.addAttribute("jobs", new CreateJobDTO());
        return "company/jobs";
    }

    @PostMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String createJob(CreateJobDTO createJobDTO) {
        this.createJobService.execute(createJobDTO, getToken());
        return "redirect:/company/jobs/list";
    }

    @GetMapping("/jobs/list")
    @PreAuthorize("hasRole('COMPANY')")
    public String list(Model model) {
        var result = this.listAllJobsCompanyService.execute(getToken());
        model.addAttribute("jobs", result);
        return "company/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        session.setAttribute("token", null);

        return "redirect:/company/login";
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails().toString();
    }
}
