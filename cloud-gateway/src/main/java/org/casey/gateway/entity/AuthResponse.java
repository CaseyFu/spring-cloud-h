package org.casey.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName AuthResponse
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/25 20:55
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {
    private String token;
}
