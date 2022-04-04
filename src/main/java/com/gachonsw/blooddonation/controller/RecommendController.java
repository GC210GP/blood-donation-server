package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.RecommendResponseDto;
import com.gachonsw.blooddonation.service.FlaskRelayService;
import com.gachonsw.blooddonation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final FlaskRelayService flaskRelayService;

//    @GetMapping("/")
//    public Result<?> recommend(@RequestParam String userId){
//        return new Result<>(flaskRelayService.getRecommend(userId));
//    }

//    @GetMapping("/")
//    public Result<?> recommend(@RequestParam String userId){
//        List<User> userList = new ArrayList<>();
//
//        User user1 = userService.findById(4L);
//        User user2 = userService.findById(6L);
//        User user3 = userService.findById(8L);
//        User user4 = userService.findById(10L);
//        User user5 = userService.findById(12L);
//        User user6 = userService.findById(14L);
//        User user7 = userService.findById(16L);
//        User user8 = userService.findById(18L);
//        User user9 = userService.findById(20L);
//        User user10 = userService.findById(22L);
//
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);
//        userList.add(user4);
//        userList.add(user5);
//        userList.add(user6);
//        userList.add(user7);
//        userList.add(user8);
//        userList.add(user9);
//        userList.add(user10);
//
//        List<UserResponseDto> collect = userList.stream()
//                .map(UserResponseDto::new)
//                .collect(Collectors.toList());
//
//        return new Result<>(collect);
//    }

    //유저 트레이닝 하고 추천 결과 리턴
    @PostMapping
    public ResponseEntity<?> trainUserAndRecommend(){
        RecommendResponseDto res = flaskRelayService.trainUserAndRecommend();
        return ResponseEntity.ok().body(res);
    }

//    @PostMapping("/add-data")
//    public UserResponse postHello(@RequestBody String userId){
//        return templateService.post();
//    }
}