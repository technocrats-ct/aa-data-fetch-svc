package com.technocrats.fidata.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FIStatusResponse {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Account {
        private String linkRefNumber;

        @JsonProperty(value = "FIStatus")
        private String FIStatus;

        private String description;
    }

    private String fipID;

    @JsonProperty(value = "Accounts")
    private List<FIStatusResponse.Account> Accounts;

}
