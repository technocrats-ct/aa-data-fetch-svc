package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.dtos.FiDataFetchResponse;
import com.technocrats.fidata.dtos.aa.AAFiDataRespDto;
import com.technocrats.fidata.dtos.aa.FetchedDataDto;
import com.technocrats.fidata.dtos.dhe.ErrorInfo;
import com.technocrats.fidata.services.AaSvc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FetchFiData implements IProcessFiDataRequest {
    private final AaSvc aaSvc;

    @Override
    public Integer getExecutionSeq() {
        return 3;
    }

    @Override
    public boolean chain(FiDataFetchResponse fiDataFetchResponse) {
        try {
            AAFiDataRespDto aaFiDataResp = fiDataFetchResponse.getDataFetchResp();
            String sessionId = aaFiDataResp.getSessionId();
            FetchedDataDto fetchedData = aaSvc.fetchDataForSession(sessionId);
            log.info("Fetched Encrypted Data from AA: {}", fetchedData);
            // finally set the fetched data
            fiDataFetchResponse.setFetchedFIData(fetchedData);
            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error in Fetching the Financial Data: %s", ex.getMessage());
            log.error(errorMessage);
            fiDataFetchResponse.setErrorInfo(new ErrorInfo("1", errorMessage));
            return false;
        }
    }
}
