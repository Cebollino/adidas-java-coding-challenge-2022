package com.adidas.backend.prioritysaleservice.controller;

import com.adidas.backend.prioritysaleservice.service.prioritySale.PrioritySaleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PrioritySaleRestController.class)
public class PrioritySaleRestControllerTest {

    @Autowired
    private PrioritySaleRestController prioritySaleRestController;

    @MockBean
    private PrioritySaleService prioritySaleService;

    @Test
    void addMemberToQueueOk() {
        when(prioritySaleService.addMemberToQueue("example@adiclub.com")).thenReturn(true);

        ResponseEntity<String> responseEntity = prioritySaleRestController.addMemberToQueue("example@adiclub.com");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Succesfully added to queue", responseEntity.getBody());
    }

    @Test
    void addMemberToQueueKo() {
        when(prioritySaleService.addMemberToQueue("Couldn't add member to queue :(")).thenReturn(false);

        ResponseEntity<String> responseEntity = prioritySaleRestController.addMemberToQueue("example@adiclub.com");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Couldn't add member to queue :(", responseEntity.getBody());
    }

    @Test
    void addMemberToQueueUnable() {
        when(prioritySaleService.addMemberToQueue("example@adiclub.com")).thenReturn(null);

        ResponseEntity<String> responseEntity = prioritySaleRestController.addMemberToQueue("example@adiclub.com");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("You are already in queue, we will send a email in case you are the winner", responseEntity.getBody());
    }

    @Test
    void getWinnerOk() {
        when(prioritySaleService.getWinner()).thenReturn("example@adiclub.com");

        ResponseEntity<String> responseEntity = prioritySaleRestController.getWinner();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Winner is example@adiclub.com", responseEntity.getBody());
    }

    @Test
    void getWinnerKo() {
        when(prioritySaleService.getWinner()).thenReturn(null);

        ResponseEntity<String> responseEntity = prioritySaleRestController.getWinner();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Couldn't get winner!", responseEntity.getBody());
    }

}
