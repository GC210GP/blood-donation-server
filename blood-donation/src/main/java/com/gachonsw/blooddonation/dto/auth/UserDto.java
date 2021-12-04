//package com.gachonsw.blooddonation.dto.auth;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.*;
//import mangpo.server.entity.User;
//
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserDto {
//
//
//   private String email;
//
//   private String password;
//
//   @NotNull
//   @Size(min = 3, max = 50)
//   private String nickname;
//
//   private Set<AuthorityDto> authorityDtoSet;
//
//   public static UserDto from(User user) {
//      if(user == null) return null;
//
//      return UserDto.builder()
//              .email(user.getEmail())
//              .nickname(user.getNickname())
//              .authorityDtoSet(user.getAuthorities().stream()
//                      .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
//                      .collect(Collectors.toSet()))
//              .build();
//   }
//}