package com.technocrats.fidata.dtos.dhe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {

    private String errorCode;
    private String errorMessage;

}
