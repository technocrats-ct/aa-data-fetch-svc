package com.technocrats.fidata.services;

import com.technocrats.fidata.data.FiFetchDetails;
import com.technocrats.fidata.dtos.ConsentArtifact;
import com.technocrats.fidata.data.FiDataRequestDetails;
import com.technocrats.fidata.dtos.FIStatusNotification;
import com.technocrats.fidata.repo.FiDataFetchRepo;
import com.technocrats.fidata.repo.FiFetchRepo;
import com.technocrats.fidata.services.rules.IProcessFiDataRequest;
import com.technocrats.fidata.services.rules.IProcessFiFetch;
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
    private final FiFetchRepo fiFetchRepo;
    private final List<IProcessFiDataRequest> requestProcessors;
    private final List<IProcessFiFetch> fiFetchProcessors;

    public void sendDataFetchRequest(ConsentArtifact consentDetail) {

        FiDataRequestDetails fiDataRequestDetails = new FiDataRequestDetails();
        fiDataRequestDetails.setId(UUID.randomUUID().toString());
        fiDataRequestDetails.setCreatedDate(new Date());
        fiDataRequestDetails.setConsentDetail(consentDetail);

        List<IProcessFiDataRequest> sortedSvcs = requestProcessors.stream()
                .sorted(Comparator.comparing(IProcessFiDataRequest::getExecutionSeq))
                .collect(Collectors.toList());

        for (IProcessFiDataRequest svc : sortedSvcs) {
            boolean result = svc.chain(fiDataRequestDetails);
            if (!result) break;
        }

        log.info("FiDataRequestDetails: {}", fiDataRequestDetails);
        fiDataFetchRepo.save(fiDataRequestDetails);
    }

    public void fetchFiData(FIStatusNotification fiStatusNotification) {

        FiFetchDetails fiFetchDetails = new FiFetchDetails();
        fiFetchDetails.setId(UUID.randomUUID().toString());
        fiFetchDetails.setCreatedDate(new Date());
        fiFetchDetails.setFiStatusNotification(fiStatusNotification);

        List<IProcessFiFetch> sortedSvcs = fiFetchProcessors.stream()
                .sorted(Comparator.comparing(IProcessFiFetch::getExecutionSeq))
                .collect(Collectors.toList());

        for (IProcessFiFetch svc : sortedSvcs) {
            boolean result = svc.chain(fiFetchDetails);
            if (!result) break;
        }

        log.info("Fetched Fi Data: {}", fiFetchDetails);
        fiFetchRepo.save(fiFetchDetails);

    }

}