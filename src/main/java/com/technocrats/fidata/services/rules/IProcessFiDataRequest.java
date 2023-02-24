package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.dtos.FiDataFetchResponse;

public interface IProcessFiDataRequest {

    Integer getExecutionSeq();
    boolean chain(FiDataFetchResponse fiDataFetchResponse);
}
