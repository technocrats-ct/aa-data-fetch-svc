package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.dtos.FetchedFIData;
import com.technocrats.fidata.dtos.FiDataFetchResponse;
import com.technocrats.fidata.dtos.aa.FIData;
import com.technocrats.fidata.dtos.aa.FetchedDataDto;
import com.technocrats.fidata.dtos.aa.FiDataList;
import com.technocrats.fidata.dtos.dhe.DecryptReqBody;
import com.technocrats.fidata.dtos.dhe.DecryptRespBody;
import com.technocrats.fidata.dtos.dhe.ErrorInfo;
import com.technocrats.fidata.dtos.dhe.KeyMaterialWithNonce;
import com.technocrats.fidata.services.DHEncSvc;
import com.technocrats.fidata.utils.EncodeDecodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DecryptFiData implements IProcessFiDataRequest {
    private final DHEncSvc dhEncSvc;

    @Override
    public Integer getExecutionSeq() {
        return 4;
    }

    @Override
    public boolean chain(FiDataFetchResponse fiDataFetchResponse) {
        try {
            FetchedDataDto fiData = fiDataFetchResponse.getFetchedFIData();
            KeyMaterialWithNonce localKeyMaterialWithNonce = fiDataFetchResponse.getLocalKeyMaterialWithNonce();
            String localNonce = localKeyMaterialWithNonce.getNonce();
            String localPrivateKey = fiDataFetchResponse.getLocalPrivateKey();
            for (FiDataList fiDataList : fiData.getFI()) {
                KeyMaterialWithNonce remoteKeyMaterialWithNonce = fiDataList.getKeyMaterial();
                List<FIData> dataList = fiDataList.getData();
                for (FIData data : dataList) {
                    String encryptedData = data.getEncryptedFI();
                    String remoteNonce = remoteKeyMaterialWithNonce.getNonce();
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
            fiDataFetchResponse.setErrorInfo(new ErrorInfo("1", errorMessage));
            return false;
        }
    }
}
