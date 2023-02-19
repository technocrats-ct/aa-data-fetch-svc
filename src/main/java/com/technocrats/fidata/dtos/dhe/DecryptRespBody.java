package com.technocrats.fidata.dtos.dhe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecryptRespBody {

    private String base64Data;

    private ErrorInfo errorInfo;

}
