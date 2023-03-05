package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.data.FiDataRequestDetails;
import com.technocrats.fidata.data.FiFetchDetails;
import com.technocrats.fidata.repo.FiDataFetchRepo;
import com.technocrats.fidata.repo.FiFetchRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadKeyMaterialForSession implements IProcessFiFetch {

    private final FiDataFetchRepo fiDataFetchRepo;

    @Override
    public Integer getExecutionSeq() {
        return 1;
    }

    @Override
    public boolean chain(FiFetchDetails fiFetchDetails) {
        try {
            String sessionId = fiFetchDetails.getFiStatusNotification().getSessionId();
            FiDataRequestDetails fiDataRequestDetails = fiDataFetchRepo.findByDataFetchRespSessionId(sessionId);
            fiFetchDetails.setFiDataRequestDetails(fiDataRequestDetails);
            log.info("Fetched corresponding data request(with key material) for sessionId: {}", sessionId);
            return true;
        } catch (Exception ex) {
            String errorMsg = String.format("Error in loading key material for session: %s", fiFetchDetails);
            log.error(errorMsg);
            return false;
        }
    }
}
