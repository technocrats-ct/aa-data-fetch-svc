package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.data.FiDataRequestDetails;

public interface IProcessFiDataRequest {

    Integer getExecutionSeq();

    boolean chain(FiDataRequestDetails fiDataRequestDetails);
}
