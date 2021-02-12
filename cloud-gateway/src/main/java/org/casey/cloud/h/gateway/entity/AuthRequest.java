package org.casey.cloud.h.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName AuthRequest
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/25 20:55
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthRequest {
    private String username;
    private String password;
}
