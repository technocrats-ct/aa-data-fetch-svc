package com.technocrats.fidata.services.rules;

public interface IProcessFiDataRequest {

    Integer getExecutionSeq();
    void chain();
}
