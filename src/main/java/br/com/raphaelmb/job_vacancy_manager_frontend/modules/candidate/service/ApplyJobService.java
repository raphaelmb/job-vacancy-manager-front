package br.com.raphaelmb.job_vacancy_manager_frontend.modules.candidate.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplyJobService {
    @Value("${host.api.job.vacancy}")
    private String hostApi;

    public String execute(String token, UUID jobId) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<UUID> request = new HttpEntity<>(jobId, headers);

        String url = hostApi.concat("/candidate/job/apply");

        return rt.postForObject(url, request, String.class);

    }
}
