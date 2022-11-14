package com.adidas.backend.prioritysaleservice.service.prioritySale;


public interface PrioritySaleService {
    Boolean addMemberToQueue(String emailAddress);
    String getWinner();
}
