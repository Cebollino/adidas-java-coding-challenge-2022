package com.adidas.backend.prioritysaleservice.service.prioritySale.impl;

import com.adidas.backend.prioritysaleservice.controller.PrioritySaleRestController;
import com.adidas.backend.prioritysaleservice.controller.dto.AdiClubMemberInfoDto;
import com.adidas.backend.prioritysaleservice.service.prioritySale.PrioritySaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrioritySaleServiceImpl implements PrioritySaleService {

    @Autowired
    private RestTemplate restTemplate;

    private final static Logger logger = LoggerFactory.getLogger(PrioritySaleRestController.class);
    private List<AdiClubMemberInfoDto> queue = new ArrayList<>();


    @Override
    public Boolean addMemberToQueue(String emailAddress) {
        ResponseEntity<AdiClubMemberInfoDto> adiClubMemberResponse =
                restTemplate.getForEntity("http://adidas-be-challenge-adiclubservice/adiclub?emailAddress="+emailAddress+"", AdiClubMemberInfoDto.class);

        AdiClubMemberInfoDto memberInfoDto = adiClubMemberResponse.getBody();

        if (memberInfoDto != null) {
            if (!memberInQueue(memberInfoDto.getEmail())) {
                queue.add(memberInfoDto);

                reorderQueue(memberInfoDto);

                return true;
            } else {
                return null;
            }
        } else {
            return false;
        }
    }

    @Override
    public String getWinner() {
        String firstMemberEmail = queue.get(0).getEmail();
        ResponseEntity<String> sendEmailResponse;

        try {
            sendEmailResponse =
                    restTemplate.getForEntity("http://adidas-be-challenge-emailservice/sendEmail?emailAddress=" + firstMemberEmail + "", String.class);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) logger.error("Invocation to emailservice returns: ["+e.getMessage()+"]");
            return null;
        }

        if (sendEmailResponse.getStatusCode().equals(HttpStatus.OK)) {
            queue.remove(0);

            return firstMemberEmail;
        } else {
            return null;
        }
    }

    /**
     * Check if the emailAddress is already on the queue so we dont add the user multiple times
     * @param emailAddress
     * @return
     */
    private boolean memberInQueue(String emailAddress) {
        return queue.stream().anyMatch(member -> member.getEmail().equals(emailAddress));
    }

    /**
     * Reorders the queue based on these parameters:
     *     AadiClub members have priority over non-members
     *     Within adiClub members, the ones with higher number of points have priority.
     *     In case of members having same number of points, oldest registration date has priority
     * @param memberInfoDto
     */
    private void reorderQueue(AdiClubMemberInfoDto memberInfoDto) {
        List<AdiClubMemberInfoDto> nonAdiClubUsers = queue.stream()
                .filter(member -> "@adiClub.".contains(member.getEmail()))
                .collect(Collectors.toList());

        List<AdiClubMemberInfoDto> adiClubUsers = queue.stream()
                .filter(member -> "@adiClub.".contains(member.getEmail()))
                .collect(Collectors.toList());

       Comparator<AdiClubMemberInfoDto> byPoints = Comparator.comparing(AdiClubMemberInfoDto::getPoints);
       Comparator<AdiClubMemberInfoDto> byDate = Comparator.comparing(AdiClubMemberInfoDto::getRegistrationDate);

       adiClubUsers.sort(byPoints.thenComparing(byDate));

       queue = new ArrayList<>();

       queue.addAll(adiClubUsers);
       queue.addAll(nonAdiClubUsers);
    }
}
