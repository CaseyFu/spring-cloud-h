package org.casey.cloud.h.auth.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.casey.cloud.h.common.core.Result;
import org.casey.cloud.h.common.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @ClassName AuthController
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/5 17:12
 */

@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证
     */
    @PostMapping("/token")
    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Assert.notNull(accessToken, "accessToken为null!");
        ObjectNode tokenObject = JsonUtil.objectNode();
        tokenObject.put("access_token", accessToken.getValue());
        tokenObject.put("refresh_token", accessToken.getRefreshToken().getValue());
        tokenObject.put("expires_in", accessToken.getExpiresIn());
        tokenObject.put("token_type", accessToken.getTokenType());
        tokenObject.put("scope", accessToken.getScope().toString());
        return Result.success(tokenObject, "获取成功!");
    }
}