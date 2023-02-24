package com.technocrats.fidata.controller;

import com.technocrats.fidata.dtos.ConsentDetailDTO;
import com.technocrats.fidata.dtos.FiDataFetchResponse;
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
    public FiDataFetchResponse fetchFIDataForConsent(@RequestBody ConsentDetailDTO consentDetail) {
        log.info("Consent Detail Received: {}", consentDetail);
        return fiService.fetchFIDataForConsent(consentDetail);
    }

}
