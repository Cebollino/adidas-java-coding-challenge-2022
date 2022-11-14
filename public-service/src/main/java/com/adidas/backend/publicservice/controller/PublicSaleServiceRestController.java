package com.adidas.backend.publicservice.controller;

import com.adidas.backend.publicservice.service.publicservice.impl.PublicServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicSaleServiceRestController {

  private final PublicServiceImpl publicService;

  public PublicSaleServiceRestController(PublicServiceImpl publicService) {
    this.publicService = publicService;
  }

  @GetMapping("/entrySale")
  public ResponseEntity<String> entrySale(@RequestParam(value = "emailAddress") final String emailAddress) {
    String serviceResponse = publicService.callPrioritySaleService(emailAddress);

    return "Couldn't add member to queue :(".equals(serviceResponse) ? ResponseEntity.internalServerError().body(serviceResponse) :ResponseEntity.ok().body(serviceResponse);
  }

  @GetMapping("/getWinner")
  public ResponseEntity<String> getWinner() {
    String serviceResponse = publicService.getWinner();

    return "Couldn't get winner!".equals(serviceResponse) ? ResponseEntity.internalServerError().body(serviceResponse) : ResponseEntity.ok().body(serviceResponse);
  }
}
