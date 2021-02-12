package org.casey.cloud.h.gateway.entity;// package org.casey.cloudoauth2gateway.entity;
//
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.ToString;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
//
// import java.util.Collection;
//
// /**
//  * @ClassName User
//  * @Author Fu Kai
//  * @Description todo
//  * @Date 2020/12/25 20:52
//  */
//
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
// public class User implements UserDetails {
//     private Long id;
//     private String name;
//     private String password;
//     private Integer age;
//
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return null;
//     }
//
//     @Override
//     public String getPassword() {
//         return this.password;
//     }
//
//     @Override
//     public String getUsername() {
//         return this.name;
//     }
//
//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }
//
//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }
//
//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }
//
//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
// }
