package br.com.raphaelmb.job_vacancy_manager_frontend.modules.candidate.service;


import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import br.com.raphaelmb.job_vacancy_manager_frontend.modules.candidate.dto.ProfileUserDTO;

@Service
public class ProfileCandidateService {
    public ProfileUserDTO execute(String token) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);
        
        try {
            var result = rt.exchange("http://localhost:8080/candidate/", HttpMethod.GET, request, ProfileUserDTO.class);
            return result.getBody();
        } catch(Unauthorized e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
    
}
