package com.technocrats.fidata.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentDetailDTO {

    @JsonProperty(value = "Consent")
    private Consent Consent;

    @JsonProperty(value = "FIDataRange")
    private FIDataRangeDTO FIDataRangeDTO;

}
