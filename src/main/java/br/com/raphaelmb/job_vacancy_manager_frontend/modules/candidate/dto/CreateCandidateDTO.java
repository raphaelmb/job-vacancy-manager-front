package br.com.raphaelmb.job_vacancy_manager_frontend.modules.candidate.dto;

import lombok.Data;

@Data
public class CreateCandidateDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private String description;
}
