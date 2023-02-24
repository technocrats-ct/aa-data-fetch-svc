package com.technocrats.fidata.repo;

import com.technocrats.fidata.dtos.FiDataFetchResponse;
import com.technocrats.fidata.dtos.aa.FetchedDataDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiDataFetchRepo extends MongoRepository<FiDataFetchResponse, String> {
}
