package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.CreateLikeDto;
import com.gachonsw.blooddonation.dto.LikeResponseDto;
import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.dto.UserLikedResponseDto;
import com.gachonsw.blooddonation.entity.Liked;
import com.gachonsw.blooddonation.service.LikedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/likes")
public class LikeController {

    private final LikedService likedService;

    @PostMapping("/likes")
    public ResponseEntity<LikeResponseDto> createLike(@RequestBody CreateLikeDto createLikeDto, UriComponentsBuilder b){
        Long likeId = likedService.createLike(createLikeDto);

        UriComponents uriComponents =
                b.path("/likes/{likeId}").buildAndExpand(likeId);

        LikeResponseDto res = new LikeResponseDto(likedService.findById(likeId));
        return ResponseEntity.created(uriComponents.toUri()).body(res);
    }

    @GetMapping("/likes/{likeId}")
    public Result<LikeResponseDto> getLike(@PathVariable Long likeId){
        Liked liked = likedService.findById(likeId);
        return new Result<>(new LikeResponseDto(liked));
    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<?> deleteLike(@PathVariable Long likeId){
        likedService.deleteLikeById(likeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/likes")
    public Result<UserLikedResponseDto> getUserLikeList(@PathVariable Long userId){
        List<Liked> likedList = likedService.findListByUser(userId);
        UserLikedResponseDto res = new UserLikedResponseDto(likedList);

//        List<LikeResponseDto> res = likeService.findListByUser(userId).stream()
//                .map(LikeResponseDto::new)
//                .collect(Collectors.toList());

        return new Result<>(res);
    }
}
