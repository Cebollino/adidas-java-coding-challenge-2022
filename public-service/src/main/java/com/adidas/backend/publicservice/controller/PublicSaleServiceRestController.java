package com.adidas.backend.publicservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PublucSaleServiceRestController {

  public static final String PRIORITY_SALE_SERVICE_URL = "http://adidas-be-challenge-prioritysaleservice/prioritySale";

  @GetMapping("/entrySale")
  public ResponseEntity<String> entrySale(@RequestParam(value = "emailAddress") final String emailAddress) {
    ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(UriComponentsBuilder.fromHttpUrl(PRIORITY_SALE_SERVICE_URL).queryParam("emailAddress", emailAddress).encode().toUriString(), String.class);

    return ResponseEntity.ok().body(responseEntity.getBody());
  }
}
