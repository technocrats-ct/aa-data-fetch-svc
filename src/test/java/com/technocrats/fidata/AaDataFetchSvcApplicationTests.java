package com.technocrats.fidata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technocrats.fidata.dtos.aa.FetchedDataDto;
import com.technocrats.fidata.dtos.dhe.GeneratedDHEKeyPair;
import com.technocrats.fidata.services.AaSvc;
import com.technocrats.fidata.services.DHEncSvc;
import com.technocrats.fidata.utils.EncodeDecodeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class AaDataFetchSvcApplicationTests {


    @Test
    void contextLoads() {
    }


}
