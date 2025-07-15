package br.com.raphaelmb.job_vacancy_manager_frontend.modules.candidate.dto;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private UUID id;
    private String description;
    private String benefits;
    private String level;
    private UUID companyId;
    private Date createdAt;
}
