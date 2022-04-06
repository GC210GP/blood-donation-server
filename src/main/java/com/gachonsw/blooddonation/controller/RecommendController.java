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

    //유저 트레이닝 하고 추천 결과 리턴
    @PostMapping
    public ResponseEntity<?> trainUserAndRecommend(){
        RecommendResponseDto res = flaskRelayService.trainUserAndRecommend();
        return ResponseEntity.ok().body(res);
    }

    //모델 업데이트 명령
    @PostMapping("/model")
    public ResponseEntity<?> updateModel(){
        return flaskRelayService.updateModel();
    }

//    @PostMapping("/add-data")
//    public UserResponse postHello(@RequestBody String userId){
//        return templateService.post();
//    }
}