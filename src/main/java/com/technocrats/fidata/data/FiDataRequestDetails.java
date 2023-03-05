package com.technocrats.fidata.data;

import com.technocrats.fidata.dtos.ConsentArtifact;
import com.technocrats.fidata.dtos.ErrorInfo;
import com.technocrats.fidata.dtos.FiDataRespBody;
import com.technocrats.fidata.dtos.KeyMaterialWithNonce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "FiDataRequestDetails")
public class FiDataRequestDetails {

    @Id
    private String id;

    @CreatedDate
    private Date createdDate;

    private ConsentArtifact consentDetail;

    private KeyMaterialWithNonce localKeyMaterialWithNonce;

    private String localPrivateKey;

    private FiDataRespBody dataFetchResp;

    private ErrorInfo errorInfo = null;

}
