package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.data.FiDataRequestDetails;
import com.technocrats.fidata.dtos.ErrorInfo;
import com.technocrats.fidata.dtos.GeneratedDHEKeyPair;
import com.technocrats.fidata.dtos.KeyMaterial;
import com.technocrats.fidata.dtos.KeyMaterialWithNonce;
import com.technocrats.fidata.services.DHEncSvc;
import com.technocrats.fidata.utils.EncodeDecodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenerateLocalKeyNdNonce implements IProcessFiDataRequest {

    private final DHEncSvc dhEncSvc;

    @Override
    public Integer getExecutionSeq() {
        return 1;
    }

    @Override
    public boolean chain(FiDataRequestDetails fiDataRequestDetails) {
        try {
            GeneratedDHEKeyPair localDHEKeyPair = dhEncSvc.generateKey();
            log.info("Local KeyPair Generated: {}", localDHEKeyPair);

            if (localDHEKeyPair.getErrorInfo() != null) {
                String errorMessage = String.format("There was an Error in Generating DHE KeyPair: %s", localDHEKeyPair.getErrorInfo().getErrorMessage());
                log.error(errorMessage);
                fiDataRequestDetails.setErrorInfo(new ErrorInfo("1", errorMessage));
                return false;
            }

            String localNonce = EncodeDecodeUtils.getRandomNonce(44);
            log.info("Local Nonce Generated: {}", localNonce);

            KeyMaterial localKeyMaterial = localDHEKeyPair.getKeyMaterial();
            KeyMaterialWithNonce localKeyMaterialWithNonce = new KeyMaterialWithNonce(localKeyMaterial, localNonce);
            log.info("Local KeyMaterial with Nonce to be sent to AA: {}", localKeyMaterialWithNonce);

            // finally set the local key material + nonce + private key info in data
            fiDataRequestDetails.setLocalKeyMaterialWithNonce(localKeyMaterialWithNonce);
            fiDataRequestDetails.setLocalPrivateKey(localDHEKeyPair.getPrivateKey());

            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error occurred in creating new local Key material: %s", ex.getMessage());
            log.error(errorMessage);
            fiDataRequestDetails.setErrorInfo(new ErrorInfo("1", errorMessage));
            return false;
        }
    }
}
