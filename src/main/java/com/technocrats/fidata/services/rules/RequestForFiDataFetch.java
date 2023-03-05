package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.dtos.*;
import com.technocrats.fidata.data.FiDataRequestDetails;
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
    public boolean chain(FiDataRequestDetails fiDataRequestDetails) {
        try {
            ConsentArtifact consentDetail = fiDataRequestDetails.getConsentDetail();

            FIDataRange fiDataRange = consentDetail.getFIDataRange();
            Consent consent = consentDetail.getConsent();

            KeyMaterialWithNonce localKeyMaterialWithNonce = fiDataRequestDetails.getLocalKeyMaterialWithNonce();

            FiDataReqBody fiDataReqBody = new FiDataReqBody("1.1.2", new Date(), UUID.randomUUID().toString(), fiDataRange, consent, localKeyMaterialWithNonce);
            log.info("FI Data Request Payload for AA: {} ", fiDataReqBody);

            FiDataRespBody dataReqResponse = aaSvc.sendDataFetchReq(fiDataReqBody);
            log.info("Response received from Data Fetch POST Request(containing session id): {}", dataReqResponse);

            // finally set the request response containing sessionId
            fiDataRequestDetails.setDataFetchResp(dataReqResponse);

            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error in Sending the Data Fetch Request: %s", ex.getMessage());
            log.error(errorMessage);
            fiDataRequestDetails.setErrorInfo(new ErrorInfo("1", errorMessage));
            return false;
        }
    }
}
