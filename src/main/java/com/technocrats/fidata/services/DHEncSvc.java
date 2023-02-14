package com.technocrats.fidata.services;

import com.technocrats.fidata.constants.AaConstants;
import com.technocrats.fidata.dtos.dhe.DecryptReqBody;
import com.technocrats.fidata.dtos.dhe.DecryptRespBody;
import com.technocrats.fidata.dtos.dhe.GeneratedDHEKeyPair;
import com.technocrats.fidata.dtos.dhe.KeyMaterial;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DHEncSvc {

    private final WebClient webClientDHE;

    public GeneratedDHEKeyPair generateKey() {
        GeneratedDHEKeyPair generatedDHEKeyPair = webClientDHE.get().uri(AaConstants.RAHASYA_KEYGEN_ECC_URI)
                .retrieve().bodyToMono(GeneratedDHEKeyPair.class).block();
        // log to mongodb here.
        return generatedDHEKeyPair;
    }

    public DecryptRespBody decryptData(DecryptReqBody decryptReqBody) {

//        DecryptReqBody decryptionReq = DecryptReqBody.builder()
//                .base64Data(encryptedData)
//                .base64RemoteNonce(remoteNonce)
//                .base64YourNonce(localNonce)
//                .ourPrivateKey(ourKeyDetails.getPrivateKey())
//                .remoteKeyMaterial(remoteKeyMaterial)
//                .build();

        DecryptRespBody response = webClientDHE.post()
                .uri(AaConstants.RAHASYA_KEYGEN_DECRYPT_URI)
                .body(Mono.just(decryptReqBody), DecryptReqBody.class)
                .retrieve()
                .bodyToMono(DecryptRespBody.class)
                .block();

        return response;
    }


}
