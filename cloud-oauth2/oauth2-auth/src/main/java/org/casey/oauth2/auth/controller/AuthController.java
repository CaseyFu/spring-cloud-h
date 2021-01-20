// package org.casey.cloudoauth2auth.controller;
//
// import org.casey.cloudoauth2auth.api.CommonResult;
// import org.casey.cloudoauth2auth.domain.Oauth2TokenDto;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.oauth2.common.OAuth2AccessToken;
// import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
// import org.springframework.web.HttpRequestMethodNotSupportedException;
// import org.springframework.web.bind.annotation.*;
//
// import java.security.Principal;
// import java.util.Map;
//
// /**
//  * @ClassName AuthController
//  * @Author Fu Kai
//  * @Description todo
//  * @Date 2021/1/5 17:12
//  */
//
// @RestController
// @RequestMapping("/oauth")
// public class AuthController {
//
//     @Autowired
//     private TokenEndpoint tokenEndpoint;
//
//     /**
//      * Oauth2登录认证
//      */
//     @PostMapping("/token")
//     public CommonResult<Oauth2TokenDto> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//         OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
//         Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
//                 .token(oAuth2AccessToken.getValue())
//                 .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
//                 .expiresIn(oAuth2AccessToken.getExpiresIn())
//                 .tokenHead("Bearer ").build();
//
//         return CommonResult.success(oauth2TokenDto);
//     }
// }