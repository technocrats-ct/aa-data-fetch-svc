package com.technocrats.fidata.dtos.dhe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class KeyMaterialWithNonce extends KeyMaterial {

    public KeyMaterialWithNonce(KeyMaterial.DHPublicKey DHPublicKey, String cryptoAlg, String curve, String params, String nonce) {
        super(DHPublicKey, cryptoAlg, curve, params);
        Nonce = nonce;
    }

    public KeyMaterialWithNonce(KeyMaterial material, String nonce) {
        this(material.getDHPublicKey(), material.getCryptoAlg(), material.getCurve(), material.getParams(), nonce);
    }

    @JsonProperty(value = "Nonce")
    private String Nonce;


}
