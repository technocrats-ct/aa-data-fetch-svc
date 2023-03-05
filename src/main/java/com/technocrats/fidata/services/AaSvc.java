package com.technocrats.fidata.services;

import com.technocrats.fidata.dtos.FiDataReqBody;
import com.technocrats.fidata.dtos.FiDataRespBody;
import com.technocrats.fidata.dtos.FiFetchRespBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.technocrats.fidata.constants.AaConstants.*;

@Service
@RequiredArgsConstructor
public class AaSvc {

    private final WebClient webClientAA;

    public String checkHeartBeat() {
        return webClientAA.get()
                .uri(AA_HEART_BEAT_URI)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public FiDataRespBody sendDataFetchReq(FiDataReqBody FiDataReqBody) {
        return webClientAA
                .post()
                .uri(AA_FI_DATA_FETCH_REQ_URI)
                .body(Mono.just(FiDataReqBody), FiDataReqBody.class)
                .retrieve()
                .bodyToMono(FiDataRespBody.class)
                .block();
    }

    public FiFetchRespBody fetchDataForSession(String sessionId) {
        return webClientAA.get()
                .uri(AA_FI_DATA_FETCH_FOR_SESSION_URI + "/" + sessionId)
                .retrieve()
                .bodyToMono(FiFetchRespBody.class)
                .block();
    }
}


