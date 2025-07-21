package br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.dto.CreateJobDTO;

@Service
public class CreateJobService {
    public String execute(CreateJobDTO createJobDTO, String token) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<CreateJobDTO> request = new HttpEntity<>(createJobDTO, headers);

        return rt.postForObject("http://localhost:8080/company/job/", request, String.class);
    }
}
