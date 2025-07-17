package br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.dto;

import lombok.Data;

@Data
public class CreateCompanyDTO {
    private String name;
    private String username;
    private String website;
    private String email;
    private String password;
    private String description;
}
