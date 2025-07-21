package br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String description;
    private String level;
    private String benefits;
}
