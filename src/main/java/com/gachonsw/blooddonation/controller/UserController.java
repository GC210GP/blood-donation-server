package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.*;
import com.gachonsw.blooddonation.entity.SnsType;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.entity.UserSns;
import com.gachonsw.blooddonation.repository.UserSnsRepository;
import com.gachonsw.blooddonation.service.UserService;
import com.gachonsw.blooddonation.service.UserSnsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserSnsService userSnsService;

    @GetMapping
    public ResponseEntity<Result<UserResponseDto>> getUser(@RequestParam Long userId) {
        User user = userService.findById(userId);
        UserResponseDto userResponseDto = new UserResponseDto(user);

        return ResponseEntity.ok(new Result<>(userResponseDto));
    }

    @PostMapping
    public ResponseEntity<Result<UserResponseDto>> createUser(@RequestBody CreateUserDto createUserDto, UriComponentsBuilder b) {
        User user = createUserDto.toEntity(createUserDto);
        Long userId = userService.createUser(user);

        UriComponents uriComponents =
                b.path("/users/{userId}").buildAndExpand(userId);

        Result<UserResponseDto> result = new Result<>(new UserResponseDto(user));
        return ResponseEntity.created(uriComponents.toUri()).body(result);
    }


    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser(userId, userUpdateDto);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/change-user-dormant")
    public ResponseEntity<?> changeDormant(@PathVariable Long userId) {
        userService.changeDormant(userId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sns")
    public ResponseEntity<?> createUserSns(@RequestBody CreateSnsDto createSnsDto) {
        userSnsService.createUserSns(createSnsDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sns")
    public Result<List<UserSnsResponse>> getUserSnsList(@RequestParam Long userId) {
        List<UserSns> listByUser = userSnsService.findListByUser(userId);

        List<UserSnsResponse> collect = listByUser.stream()
                .map(UserSnsResponse::new)
                .collect(Collectors.toList());

        return new Result<>(collect);
    }

    @DeleteMapping("/sns/{userSnsId}")
    public ResponseEntity<?> deleteUserSns(@PathVariable Long userSnsId){
        userSnsService.deleteUserSns(userSnsId);
        return ResponseEntity.noContent().build();
    }
}
