package com.technocrats.fidata.dtos;

import com.technocrats.fidata.dtos.aa.AAFiDataRespDto;
import com.technocrats.fidata.dtos.dhe.ErrorInfo;
import com.technocrats.fidata.dtos.dhe.KeyMaterialWithNonce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiDataFetchResponse {

    ConsentDetailDTO consentDetail;

    KeyMaterialWithNonce localKeyMaterialWithNonce;

    AAFiDataRespDto dataFetchResp;

    FetchedFIData fetchedFIData;

    ErrorInfo errorInfo = null;

}
