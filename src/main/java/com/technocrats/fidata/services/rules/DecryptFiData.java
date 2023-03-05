package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.data.FiFetchDetails;
import com.technocrats.fidata.dtos.*;
import com.technocrats.fidata.data.FiDataRequestDetails;
import com.technocrats.fidata.services.DHEncSvc;
import com.technocrats.fidata.services.rules.IProcessFiDataRequest;
import com.technocrats.fidata.utils.EncodeDecodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DecryptFiData implements IProcessFiFetch {

    private final DHEncSvc dhEncSvc;

    @Override
    public Integer getExecutionSeq() {
        return 3;
    }

    @Override
    public boolean chain(FiFetchDetails fiFetchDetails) {
        try {
            FiFetchRespBody fiData = fiFetchDetails.getFiFetchRespBody();
            FiDataRequestDetails fiDataRequestDetails = fiFetchDetails.getFiDataRequestDetails();
            KeyMaterialWithNonce localKeyMaterialWithNonce = fiDataRequestDetails.getLocalKeyMaterialWithNonce();
            String localNonce = localKeyMaterialWithNonce.getNonce();
            String localPrivateKey = fiDataRequestDetails.getLocalPrivateKey();
            for (FI FI : fiData.getFI()) {
                KeyMaterialWithNonce remoteKeyMaterialWithNonce = FI.getKeyMaterial();
                List<FIData> dataList = FI.getData();
                String remoteNonce = remoteKeyMaterialWithNonce.getNonce();
                for (FIData data : dataList) {
                    String encryptedData = data.getEncryptedFI();
                    DecryptReqBody reqBody = new DecryptReqBody(encryptedData, remoteNonce, localNonce, localPrivateKey, remoteKeyMaterialWithNonce);
                    DecryptRespBody decryptRespBody = dhEncSvc.decryptData(reqBody);
                    data.setDecryptedFI(EncodeDecodeUtils.base64Decode(decryptRespBody.getBase64Data()));
                }
            }
            log.info("Final Data: {}", fiData);
            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error in Decrypting the Fi Data: %s", ex.getMessage());
            log.error(errorMessage);
            fiFetchDetails.setErrorInfo(new ErrorInfo("1", errorMessage));
            return false;
        }
    }
}
