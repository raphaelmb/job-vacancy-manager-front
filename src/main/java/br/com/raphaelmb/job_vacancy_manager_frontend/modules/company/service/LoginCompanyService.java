package br.com.raphaelmb.job_vacancy_manager_frontend.modules.company.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.raphaelmb.job_vacancy_manager_frontend.modules.candidate.dto.Token;

@Service
public class LoginCompanyService {
    @Value("${host.api.job.vacancy}")
    private String hostApi;

    public Token execute(String username, String password) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        String url = hostApi.concat("/company/auth");

        return rt.postForObject(url, request, Token.class);
    }
    
}
