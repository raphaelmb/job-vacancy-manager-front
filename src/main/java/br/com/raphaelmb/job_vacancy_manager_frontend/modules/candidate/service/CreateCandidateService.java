package br.com.raphaelmb.job_vacancy_manager_frontend.modules.candidate.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.raphaelmb.job_vacancy_manager_frontend.modules.candidate.dto.CreateCandidateDTO;

@Service
public class CreateCandidateService {
    @Value("${host.api.job.vacancy}")
    private String hostApi;

    public void execute(CreateCandidateDTO createCandidateDTO) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCandidateDTO> request = new HttpEntity<>(createCandidateDTO, headers);

        String url = hostApi.concat("/candidate/");

        var result = rt.postForObject(url, request, String.class);

        System.out.println(result);
    }
}
