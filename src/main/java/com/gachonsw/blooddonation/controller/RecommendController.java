package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.service.FlaskRelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final FlaskRelayService flaskRelayService;

    @GetMapping("/")
    public Result<?> recommend(@RequestParam String userId){
        return new Result<>(flaskRelayService.getRecommend(userId));
    }

//    @PostMapping("/add-data")
//    public UserResponse postHello(@RequestBody String userId){
//        return templateService.post();
//    }
}
