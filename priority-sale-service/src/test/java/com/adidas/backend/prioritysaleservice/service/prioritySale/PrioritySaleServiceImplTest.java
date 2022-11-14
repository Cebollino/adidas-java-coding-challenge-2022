package com.adidas.backend.prioritysaleservice.service.prioritySale;


import com.adidas.backend.prioritysaleservice.controller.dto.AdiClubMemberInfoDto;
import com.adidas.backend.prioritysaleservice.service.prioritySale.impl.PrioritySaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrioritySaleServiceImplTest {
    @InjectMocks
    private PrioritySaleServiceImpl prioritySaleService = new PrioritySaleServiceImpl();

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private List<AdiClubMemberInfoDto> queue = new ArrayList<>();

    @Test
    void addMemberToQueueOk() {
        AdiClubMemberInfoDto member = AdiClubMemberInfoDto.builder()
                .email("example@adiclub.com")
                .points(new Random(System.nanoTime()).nextInt(5000))
                .registrationDate(Instant.now().minus(new Random(System.nanoTime()).nextInt(365), ChronoUnit.DAYS))
                .build();

        when(restTemplate.getForEntity("http://adidas-be-challenge-adiclubservice/adiclub?emailAddress=example@hotmail.com", AdiClubMemberInfoDto.class))
                .thenReturn(new ResponseEntity<>(member, HttpStatus.OK));

        Boolean serviceResponse = prioritySaleService.addMemberToQueue("example@hotmail.com");

        assertEquals(Boolean.TRUE, serviceResponse);
    }

    @Test
    void addMemberToQueueKo() {
        AdiClubMemberInfoDto member = AdiClubMemberInfoDto.builder()
                .email("example1@adiclub.com")
                .points(new Random(System.nanoTime()).nextInt(5000))
                .registrationDate(Instant.now().minus(new Random(System.nanoTime()).nextInt(365), ChronoUnit.DAYS))
                .build();

        when(restTemplate.getForEntity("http://adidas-be-challenge-adiclubservice/adiclub?emailAddress=example1@hotmail.com", AdiClubMemberInfoDto.class))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        Boolean serviceResponse = prioritySaleService.addMemberToQueue("example1@hotmail.com");

        assertEquals(Boolean.FALSE, serviceResponse);
    }
}
