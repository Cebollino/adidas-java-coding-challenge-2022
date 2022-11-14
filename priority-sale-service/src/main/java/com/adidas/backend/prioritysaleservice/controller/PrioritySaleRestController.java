package com.adidas.backend.prioritysaleservice.controller;

import com.adidas.backend.prioritysaleservice.service.prioritySale.PrioritySaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrioritySaleRestController {

    private final static Logger logger = LoggerFactory.getLogger(PrioritySaleRestController.class);
    private final PrioritySaleService prioritySaleService;

    public PrioritySaleRestController(PrioritySaleService prioritySaleService) {
        this.prioritySaleService = prioritySaleService;
    }

    @PutMapping("/addMemberToQueue")
    public ResponseEntity<String> addMemberToQueue(@RequestBody String emailAddress) {
        Boolean added = prioritySaleService.addMemberToQueue(emailAddress);

        if (Boolean.TRUE.equals(added)) {
            return ResponseEntity.ok().body("Succesfully added to queue");
        } else if (Boolean.FALSE.equals(added)) {
            return ResponseEntity.internalServerError().body("Couldn't add member to queue :(");
        } else {
            return ResponseEntity.ok().body("You are already in queue, we will send a email in case you are the winner");
        }
    }

    @GetMapping("/getWinner")
    public ResponseEntity<String> getWinner() {
        String winnersEmail = prioritySaleService.getWinner();

        if (winnersEmail != null) {
            return ResponseEntity.ok().body("Winner is "+winnersEmail+"");
        } else {
            return ResponseEntity.internalServerError().body("Couldn't get winner!");
        }
    }
}
