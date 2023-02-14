package com.technocrats.fidata.dtos.aa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.technocrats.fidata.dtos.dhe.KeyMaterialWithNonce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AAFiDataReqDto {

    private String ver;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date timestamp;

    private String txnid;

    @JsonProperty(value = "FIDataRange")
    private com.technocrats.fidata.dtos.FIDataRange FIDataRange;

    @JsonProperty(value = "Consent")
    private com.technocrats.fidata.dtos.Consent Consent;

    @JsonProperty(value = "KeyMaterial")
    private KeyMaterialWithNonce KeyMaterial;
}
