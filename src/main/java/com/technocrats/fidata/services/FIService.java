package com.technocrats.fidata.services;

import com.technocrats.fidata.dtos.ConsentDetailDTO;
import com.technocrats.fidata.dtos.aa.*;
import com.technocrats.fidata.dtos.dhe.*;
import com.technocrats.fidata.utils.EncodeDecodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FIService {

    private final AaSvc aaSvc;
    private final DHEncSvc dhEncSvc;

    public String fetchFIDataForConsent(ConsentDetailDTO consentDetailDTO) {
        GeneratedDHEKeyPair localDHEKeyPair = dhEncSvc.generateKey();
        log.info("Local KeyPair Generated: {}", localDHEKeyPair);
        if (localDHEKeyPair.getErrorInfo() != null) {
            String errorMessage = localDHEKeyPair.getErrorInfo().getErrorMessage();
            log.error("There was an Error in Generating DHE KeyPair: {}", errorMessage);
            return null;
        }
        String localNonce = EncodeDecodeUtils.getRandomNonce(44);
        log.info("Local Nonce Generated: {}", localNonce);
        KeyMaterial localKeyMaterial = localDHEKeyPair.getKeyMaterial();
        KeyMaterialWithNonce localKeyMaterialWithNonce = new KeyMaterialWithNonce(localKeyMaterial, localNonce);
        log.info("Local KeyMaterial with Nonce to be sent to AA: {}", localKeyMaterialWithNonce);
        AAFiDataReqDto aaFiDataReqDto = new AAFiDataReqDto("1.1.2", new Date(), UUID.randomUUID().toString(), consentDetailDTO.getFIDataRangeDTO(), consentDetailDTO.getConsent(), localKeyMaterialWithNonce);
        log.info("FI Data Request Payload for AA: {} ", aaFiDataReqDto);
        AAFiDataRespDto dataReqResponse = aaSvc.sendDataFetchReq(aaFiDataReqDto);
        log.info("Response received from Data Fetch POST Request(containing session id): {}", dataReqResponse);
        FetchedDataDto fetchedData = aaSvc.fetchDataForSession(dataReqResponse.getSessionId());
        log.info("Fetched Encrypted Data from AA: {}", fetchedData);
        for (FiDataList fiDataList : fetchedData.getFI()) {
            KeyMaterialWithNonce remoteKeyMaterialWithNonce = fiDataList.getKeyMaterial();
            List<FIData> dataList = fiDataList.getData();
            for (FIData data : dataList) {
                String encryptedData = data.getEncryptedFI();
                String remoteNonce = remoteKeyMaterialWithNonce.getNonce();
                DecryptReqBody reqBody = new DecryptReqBody(encryptedData, remoteNonce, localNonce, localDHEKeyPair.getPrivateKey(), remoteKeyMaterialWithNonce);
                DecryptRespBody decryptRespBody = dhEncSvc.decryptData(reqBody);
                System.out.println(EncodeDecodeUtils.base64Decode(decryptRespBody.getBase64Data()));
            }
        }
        return "done.";
    }
}