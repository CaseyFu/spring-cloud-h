package org.casey.cloud.h.oauth2.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.casey.cloud.h.common.core.Result;
import org.casey.cloud.h.oauth2.api.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/5 17:29
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/currentUser")
    public Result currentUser(HttpServletRequest request) throws JsonProcessingException {
        //从Header中获取用户信息
        String userString = request.getHeader("user");
        return Result.success(objectMapper.readValue(userString, UserDTO.class), "获取成功!");
    }

    @GetMapping("/exception")
    public void user() {
        throw new RuntimeException("运行时异常!");
    }

    @GetMapping("/result")
    public Result singleton() {
        return Result.success(null, "枚举单例Result");
    }


}