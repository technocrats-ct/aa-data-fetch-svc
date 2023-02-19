package com.technocrats.fidata.controller;

import com.technocrats.fidata.dtos.ConsentDetailDTO;
import com.technocrats.fidata.services.FIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FIController {

    private final FIService fiService;

    @PostMapping(value = "/fetchData")
    public String fetchFIDataForConsent(@RequestBody ConsentDetailDTO consentDetail) {
        log.info("Consent Detail Received: {}", consentDetail);
        String fiData = fiService.fetchFIDataForConsent(consentDetail);
        log.info("FI Data For The Consent: {}", fiData);
        return fiData;
    }

}
