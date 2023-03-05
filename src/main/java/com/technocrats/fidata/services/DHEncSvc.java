package com.technocrats.fidata.services;

import com.technocrats.fidata.constants.AaConstants;
import com.technocrats.fidata.dtos.DecryptReqBody;
import com.technocrats.fidata.dtos.DecryptRespBody;
import com.technocrats.fidata.dtos.GeneratedDHEKeyPair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DHEncSvc {

    private final WebClient webClientDHE;

    public GeneratedDHEKeyPair generateKey() {
        return webClientDHE.get()
                .uri(AaConstants.RAHASYA_KEYGEN_ECC_URI)
                .retrieve()
                .bodyToMono(GeneratedDHEKeyPair.class)
                .block();
    }

    public DecryptRespBody decryptData(DecryptReqBody decryptReqBody) {
        return webClientDHE.post()
                .uri(AaConstants.RAHASYA_DECRYPT_ECC_URI)
                .body(Mono.just(decryptReqBody), DecryptReqBody.class)
                .retrieve()
                .bodyToMono(DecryptRespBody.class)
                .block();
    }


}
