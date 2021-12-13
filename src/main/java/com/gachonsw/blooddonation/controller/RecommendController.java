package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.dto.UserResponseDto;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.service.FlaskRelayService;
import com.gachonsw.blooddonation.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final FlaskRelayService flaskRelayService;

    private final UserService userService;

//    @GetMapping("/")
//    public Result<?> recommend(@RequestParam String userId){
//        return new Result<>(flaskRelayService.getRecommend(userId));
//    }

    @GetMapping("/")
    public Result<?> recommend(@RequestParam String userId){
        List<User> userList = new ArrayList<>();

        User user1 = userService.findById(4L);
        User user2 = userService.findById(6L);
        User user3 = userService.findById(8L);
        User user4 = userService.findById(10L);
        User user5 = userService.findById(12L);
        User user6 = userService.findById(14L);
        User user7 = userService.findById(16L);
        User user8 = userService.findById(18L);
        User user9 = userService.findById(20L);
        User user10 = userService.findById(22L);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        userList.add(user7);
        userList.add(user8);
        userList.add(user9);
        userList.add(user10);

        List<UserResponseDto> collect = userList.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());

        return new Result<>(collect);
    }

//    @PostMapping("/add-data")
//    public UserResponse postHello(@RequestBody String userId){
//        return templateService.post();
//    }
}
