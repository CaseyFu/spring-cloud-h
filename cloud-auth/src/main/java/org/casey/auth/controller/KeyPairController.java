package org.casey.auth.controller;

// import com.nimbusds.jose.jwk.JWKSet;
// import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
 * @ClassName KeyPairController
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/5 17:13
 */

@RestController
public class KeyPairController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/rsa/publicKey")
    public Map<String, ?> getKey() {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        Map<String, List<Map<String, Object>>> result = new HashMap<>(2);
        List<Map<String, Object>> keys = new ArrayList<>();
        Map<String, Object> key = new HashMap<>(16);
        key.put("kty", rsaPublicKey.getAlgorithm());
        key.put("kid", "someuniqueid");
        key.put("e", Base64.getUrlEncoder().encodeToString(rsaPublicKey.getPublicExponent().toByteArray()));
        key.put("n", Base64.getUrlEncoder().encodeToString(rsaPublicKey.getModulus().toByteArray()));
        key.put("alg", "RS256");
        key.put("use", "sig");
        keys.add(key);
        result.put("keys", keys);
        return result;
    }

}