package com.technocrats.fidata.services.rules;

import com.technocrats.fidata.data.FiDataRequestDetails;
import com.technocrats.fidata.data.FiFetchDetails;

public interface IProcessFiFetch {

    Integer getExecutionSeq();

    boolean chain(FiFetchDetails fiFetchDetails);
}
