package com.technocrats.fidata.services;

import com.technocrats.fidata.dtos.ConsentDetailDTO;
import com.technocrats.fidata.dtos.FiDataFetchResponse;
import com.technocrats.fidata.dtos.aa.*;
import com.technocrats.fidata.dtos.dhe.*;
import com.technocrats.fidata.repo.FiDataFetchRepo;
import com.technocrats.fidata.services.rules.IProcessFiDataRequest;
import com.technocrats.fidata.utils.EncodeDecodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FIService {

    private final FiDataFetchRepo fiDataFetchRepo;
    private final List<IProcessFiDataRequest> requestProcessors;

    public FiDataFetchResponse fetchFIDataForConsent(ConsentDetailDTO consentDetailDTO) {
        FiDataFetchResponse fiDataFetchResponse = new FiDataFetchResponse();
        fiDataFetchResponse.setConsentDetail(consentDetailDTO);
        List<IProcessFiDataRequest> sortedSvcs = requestProcessors.stream()
                .sorted(Comparator.comparing(IProcessFiDataRequest::getExecutionSeq))
                .collect(Collectors.toList());
        for (IProcessFiDataRequest svc : sortedSvcs) {
            boolean result = svc.chain(fiDataFetchResponse);
            if (!result) break;
        }
        fiDataFetchResponse.setId(UUID.randomUUID().toString());
        fiDataFetchResponse.setCreatedDate(new Date());
        log.info("FiDataFetchResponse: {}", fiDataFetchResponse);
        fiDataFetchRepo.save(fiDataFetchResponse);
        return fiDataFetchResponse;
    }
}