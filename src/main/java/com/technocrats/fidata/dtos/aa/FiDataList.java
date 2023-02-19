package com.technocrats.fidata.dtos.aa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.technocrats.fidata.dtos.dhe.KeyMaterialWithNonce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiDataList {
    private String fipID;

    private List<FIData> data;

    @JsonProperty(value = "KeyMaterial")
    private KeyMaterialWithNonce KeyMaterial;
}
