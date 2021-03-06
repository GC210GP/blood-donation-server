package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

//   public CustomUserDetailsService(UserRepository userRepository) {
//      this.userRepository = userRepository;
//   }


   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String email) {
      return userRepository.findWithAuthByEmail(email)
         .map(user -> createUser(email, user))
         .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
   }

   private org.springframework.security.core.userdetails.User createUser(String email, User user) {
//      if (user.getIsDormant()) {
//         throw new RuntimeException(email + " -> 활성화되어 있지 않습니다.");
//      }
      List<GrantedAuthority> grantedAuthorities = user.getUserAuthorityList().stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()))
              .collect(Collectors.toList());
      return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()),
              user.getPassword(),
              grantedAuthorities);
   }
}
