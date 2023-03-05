package com.technocrats.fidata.repo;

import com.technocrats.fidata.data.FiFetchDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiFetchRepo extends MongoRepository<FiFetchDetails,String> {

}
