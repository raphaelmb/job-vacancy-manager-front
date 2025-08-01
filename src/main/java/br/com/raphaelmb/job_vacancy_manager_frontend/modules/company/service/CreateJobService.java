package br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.dto.CreateJobDTO;

@Service
public class CreateJobService {
    @Value("${host.api.job.vacancy}")
    private String hostApi;

    public String execute(CreateJobDTO createJobDTO, String token) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<CreateJobDTO> request = new HttpEntity<>(createJobDTO, headers);

        String url = hostApi.concat("/company/job");

        return rt.postForObject(url, request, String.class);
    }
}
