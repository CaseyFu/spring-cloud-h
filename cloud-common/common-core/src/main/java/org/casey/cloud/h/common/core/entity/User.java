package org.casey.cloud.h.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName User
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/22 12:40
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 7417532992725007519L;
    private Long id;
    private String name;
    private Integer age;
    private Boolean enabled;
    private LocalDateTime birthday;
}
