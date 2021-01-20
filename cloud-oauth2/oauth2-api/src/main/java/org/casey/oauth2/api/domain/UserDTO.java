package org.casey.oauth2.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @ClassName UserDTO
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/5 17:30
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<String> roles;
}