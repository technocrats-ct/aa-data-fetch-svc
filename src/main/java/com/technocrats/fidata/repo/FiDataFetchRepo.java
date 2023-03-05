package com.technocrats.fidata.repo;

import com.technocrats.fidata.data.FiDataRequestDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiDataFetchRepo extends MongoRepository<FiDataRequestDetails, String> {

    FiDataRequestDetails findByDataFetchRespSessionId(String sessionId);
}
