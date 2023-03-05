package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.data.FiDataRequestDetails;
import com.technocrats.fidata.data.FiFetchDetails;
import com.technocrats.fidata.dtos.FiDataRespBody;
import com.technocrats.fidata.dtos.FiFetchRespBody;
import com.technocrats.fidata.dtos.ErrorInfo;
import com.technocrats.fidata.services.AaSvc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FetchFiData implements IProcessFiFetch {
    private final AaSvc aaSvc;

    @Override
    public Integer getExecutionSeq() {
        return 2;
    }

    @Override
    public boolean chain(FiFetchDetails fiFetchDetails) {
        try {
            FiDataRequestDetails fiDataRequestDetails = fiFetchDetails.getFiDataRequestDetails();
            FiDataRespBody fiDataRespBody = fiDataRequestDetails.getDataFetchResp();
            String sessionId = fiDataRespBody.getSessionId();
            FiFetchRespBody fetchedData = aaSvc.fetchDataForSession(sessionId);
            log.info("Fetched Encrypted Data from AA: {}", fetchedData);
            // finally set the fetched data
            fiFetchDetails.setFiFetchRespBody(fetchedData);
            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error in Fetching the Financial Data: %s", ex.getMessage());
            log.error(errorMessage);
            fiFetchDetails.setErrorInfo(new ErrorInfo("1", errorMessage));
            return false;
        }
    }
}
