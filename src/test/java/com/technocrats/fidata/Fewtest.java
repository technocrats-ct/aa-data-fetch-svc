package com.technocrats.fidata;

import com.technocrats.fidata.utils.EncodeDecodeUtils;
import org.junit.jupiter.api.Test;

public class Fewtest {

    @Test
    public void testit(){
        String s = "RHMxM0l1dmdPTWdnczZUNm1hOWQ4YUhJamplYXJCdXQ=";
        System.out.println(s.length());
        System.out.println(s.getBytes());
        System.out.println(EncodeDecodeUtils.getRandomNonce(44));
        System.out.println("debug.");
    }

}
