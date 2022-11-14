package com.adidas.backend.publicservice.controller;

import com.adidas.backend.publicservice.controller.dto.EntrySaleDto;
import com.adidas.backend.publicservice.controller.dto.WinnerDto;
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
  public ResponseEntity<EntrySaleDto> entrySale(@RequestParam(value = "emailAddress") final String emailAddress) {
    String serviceResponse = publicService.callPrioritySaleService(emailAddress);
    EntrySaleDto outDto = EntrySaleDto.builder().errorMessage(serviceResponse).build();

    return ResponseEntity.ok().body(outDto);
  }

  @GetMapping("/getWinner")
  public ResponseEntity<WinnerDto> getWinner() {
    String serviceResponse = publicService.getWinner();
    WinnerDto outDto = WinnerDto.builder().errorMessage(serviceResponse).build();

    return ResponseEntity.ok().body(outDto);
  }
}
