package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.dtos.Consent;
import com.technocrats.fidata.dtos.ConsentDetailDTO;
import com.technocrats.fidata.dtos.FIDataRangeDTO;
import com.technocrats.fidata.dtos.FiDataFetchResponse;
import com.technocrats.fidata.dtos.aa.AAFiDataReqDto;
import com.technocrats.fidata.dtos.aa.AAFiDataRespDto;
import com.technocrats.fidata.dtos.aa.FIData;
import com.technocrats.fidata.dtos.aa.FetchedDataDto;
import com.technocrats.fidata.dtos.dhe.ErrorInfo;
import com.technocrats.fidata.dtos.dhe.KeyMaterialWithNonce;
import com.technocrats.fidata.services.AaSvc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class RequestForFiDataFetch implements IProcessFiDataRequest {

    private final AaSvc aaSvc;

    @Override
    public Integer getExecutionSeq() {
        return 2;
    }

    @Override
    public boolean chain(FiDataFetchResponse fiDataFetchResponse) {
        try {
            ConsentDetailDTO consentDetail = fiDataFetchResponse.getConsentDetail();
            FIDataRangeDTO fiDataRange = consentDetail.getFIDataRangeDTO();
            Consent consent = consentDetail.getConsent();
            KeyMaterialWithNonce localKeyMaterialWithNonce = fiDataFetchResponse.getLocalKeyMaterialWithNonce();
            AAFiDataReqDto aaFiDataReqDto = new AAFiDataReqDto("1.1.2", new Date(), UUID.randomUUID().toString(), fiDataRange, consent, localKeyMaterialWithNonce);
            log.info("FI Data Request Payload for AA: {} ", aaFiDataReqDto);
            AAFiDataRespDto dataReqResponse = aaSvc.sendDataFetchReq(aaFiDataReqDto);
            log.info("Response received from Data Fetch POST Request(containing session id): {}", dataReqResponse);
            // finally set the request response containing sessionId
            fiDataFetchResponse.setDataFetchResp(dataReqResponse);
            return true;
        } catch (Exception ex) {
            String errorMessage = String.format(
                    "Error in Sending the Data Fetch Request: %s",
                    ex.getMessage()
            );
            log.error(errorMessage);
            fiDataFetchResponse.setErrorInfo(new ErrorInfo("1", errorMessage));
            return false;
        }

    }
}
