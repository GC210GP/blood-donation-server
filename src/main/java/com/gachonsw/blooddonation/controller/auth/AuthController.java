package com.gachonsw.blooddonation.controller.auth;


import com.gachonsw.blooddonation.dto.CreateUserDto;
import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.dto.UserResponseDto;
import com.gachonsw.blooddonation.dto.auth.LoginDto;
import com.gachonsw.blooddonation.dto.auth.TokenDto;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.jwt.JwtFilter;
import com.gachonsw.blooddonation.jwt.TokenProvider;
import com.gachonsw.blooddonation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Result<UserResponseDto>> createUser(@RequestBody CreateUserDto createUserDto, UriComponentsBuilder b) {
        User user = createUserDto.toEntity(createUserDto);
        Long userId = userService.createUser(user);

        UriComponents uriComponents =
                b.path("/users/{userId}").buildAndExpand(userId);

        Result<UserResponseDto> result = new Result<>(new UserResponseDto(user));

        return ResponseEntity.created(uriComponents.toUri()).body(result);
    }


    @PostMapping
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
