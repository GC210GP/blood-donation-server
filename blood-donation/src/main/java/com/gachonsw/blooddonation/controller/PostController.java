package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.PostDto;
import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.entity.Post;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.service.PostService;
import com.gachonsw.blooddonation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, UriComponentsBuilder b) {
        Long postId = postService.createPost(postDto);

        UriComponents uriComponents =
                b.path("/posts/{postId}").buildAndExpand(postId);

        postDto.setId(postId);
        return ResponseEntity.created(uriComponents.toUri()).body(postDto);
    }

    @GetMapping
    public Result<PostDto> getPost(@RequestParam Long postId){
        Post post = postService.findPost(postId);

        PostDto postDto = new PostDto(post);
        return new Result<>(postDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto){
        postService.updatePost(postId,postDto);
        return ResponseEntity.noContent().build();
    }

}
