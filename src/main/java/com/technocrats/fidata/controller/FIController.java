package com.technocrats.fidata.controller;

import com.technocrats.fidata.dtos.FetchedFIData;
import com.technocrats.fidata.dtos.FiDataReq;
import com.technocrats.fidata.services.FIService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FIController {

    private final FIService fiService;

    @PostMapping(value = "/fetchData")
    public String fetchFIDataForConsent(@RequestBody FiDataReq consent) {
        // log to mongodb the req received
        return fiService.fetchFIDataForConsent(consent);
        // log it
       // return data;
    }

}
