package com.technocrats.fidata.services;

import com.technocrats.fidata.dtos.FetchedFIData;
import com.technocrats.fidata.dtos.FiDataReq;
import com.technocrats.fidata.dtos.aa.*;
import com.technocrats.fidata.dtos.dhe.DecryptReqBody;
import com.technocrats.fidata.dtos.dhe.DecryptRespBody;
import com.technocrats.fidata.dtos.dhe.GeneratedDHEKeyPair;
import com.technocrats.fidata.dtos.dhe.KeyMaterialWithNonce;
import com.technocrats.fidata.utils.EncodeDecodeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FIService {

    private final AaSvc aaSvc;
    private final DHEncSvc dhEncSvc;

    public String fetchFIDataForConsent(FiDataReq fiDataReq) {
        GeneratedDHEKeyPair generatedKey = dhEncSvc.generateKey();
        System.out.println(generatedKey);
        if (generatedKey.getErrorInfo() != null) {
            return null;
        }
        String nonce = "72iSuokzNCZOjY56eUNS7E6q3Y8QRrhIkF9kcyp7+EE=";
        //EncodeDecodeUtils.generateRandomNonce();
        KeyMaterialWithNonce keyMaterialWithNonce = new KeyMaterialWithNonce(generatedKey.getKeyMaterial(), nonce);

        AAFiDataReqDto aaFiDataReqDto = new AAFiDataReqDto("1.1.2", new Date(), UUID.randomUUID().toString(),
                fiDataReq.getFIDataRange(), fiDataReq.getConsent(), keyMaterialWithNonce);

        AAFiDataRespDto dataReqResponse = aaSvc.sendDataFetchReq(aaFiDataReqDto);



        FetchedDataDto fetchedData = aaSvc.fetchDataForSession(dataReqResponse.getSessionId());

        System.out.println(fetchedData);

        for (FiDataList fiDataList : fetchedData.getFI()) {
            KeyMaterialWithNonce keyMaterialWithNonce1 = fiDataList.getKeyMaterial();
            List<FIData> data = fiDataList.getData();
            for (FIData data1 : data) {
                String encryptedData = data1.getEncryptedFI();
                DecryptReqBody reqBody = new DecryptReqBody(encryptedData, keyMaterialWithNonce1.getNonce(), nonce, generatedKey.getPrivateKey(), keyMaterialWithNonce1);
                DecryptRespBody decryptRespBody = dhEncSvc.decryptData(reqBody);
                System.out.println(EncodeDecodeUtils.base64Decode(decryptRespBody.getBase64Data()));
            }
        }
        return "done.";
    }
}
