package com.technocrats.fidata.dtos;

import com.technocrats.fidata.dtos.aa.AAFiDataRespDto;
import com.technocrats.fidata.dtos.aa.FetchedDataDto;
import com.technocrats.fidata.dtos.dhe.ErrorInfo;
import com.technocrats.fidata.dtos.dhe.KeyMaterialWithNonce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "FiDataFetchDetails")
public class FiDataFetchResponse {

    @Id
    private String id;

    @CreatedDate
    private Date createdDate;

    private ConsentDetailDTO consentDetail;

    private KeyMaterialWithNonce localKeyMaterialWithNonce;

    private AAFiDataRespDto dataFetchResp;

    private FetchedDataDto fetchedFIData;

    private String localPrivateKey;

    private ErrorInfo errorInfo = null;

}
