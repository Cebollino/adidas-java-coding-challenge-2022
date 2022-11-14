package com.adidas.backend.publicservice.service.publicservice.impl;

import com.adidas.backend.publicservice.service.publicservice.PublicService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PublicServiceImpl implements PublicService {

    public static final String PRIORITY_SALE_SERVICE_URL = "http://adidas-be-challenge-prioritysaleservice/addMemberToQueue";
    public static final String PRIORITY_SALE_SERVICE_URL_WINNER = "http://adidas-be-challenge-prioritysaleservice/getWinner";

    @Override
    public String callPrioritySaleService(String emailAddress) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>(emailAddress, headers);

        ResponseEntity<String> responseEntity = new RestTemplate()
                .exchange(UriComponentsBuilder.fromHttpUrl(PRIORITY_SALE_SERVICE_URL).build().encode().toUriString(), HttpMethod.PUT, request, String.class);

        return responseEntity.getBody();
    }

    @Override
    public String getWinner() {
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(UriComponentsBuilder.fromHttpUrl(PRIORITY_SALE_SERVICE_URL_WINNER).encode().toUriString(), String.class);

        return responseEntity.getBody();
    }
}
