package com.technocrats.fidata.controller;

import com.technocrats.fidata.dtos.ConsentArtifact;
import com.technocrats.fidata.dtos.FINotificationReq;
import com.technocrats.fidata.services.FIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FIController {

    private final FIService fiService;

    @PostMapping(value = "/FiData/ConsentDetail")
    public ResponseEntity<Void> sendDataFetchRequest(@RequestBody ConsentArtifact consentDetail) {
        log.info("Consent Artifact Received: {}", consentDetail);
        fiService.sendDataFetchRequest(consentDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/FiData/FetchData")
    public ResponseEntity<Void> fetchFiData(@RequestBody FINotificationReq fiNotificationReq) {
        log.info("FI Notification received: {}", fiNotificationReq);
        fiService.fetchFiData(fiNotificationReq.getFIStatusNotification());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}


