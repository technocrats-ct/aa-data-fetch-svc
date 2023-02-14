package com.technocrats.fidata.utils;


import org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;

public class EncodeDecodeUtils {

    public static String base64Encode(String data) {
        return new String(new Base64().encode(data.getBytes()));
    }

    public static String base64Decode(String data) {
        return new String(new Base64().decode(data.getBytes()));
    }

    public static String generateRandomNonce() {
        byte[] nonce = new byte[32];
        new SecureRandom().nextBytes(nonce);
        return convertBytesToHex(nonce);
    }

    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }
}
