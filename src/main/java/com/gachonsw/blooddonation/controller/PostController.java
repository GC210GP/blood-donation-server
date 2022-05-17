package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.*;
import com.gachonsw.blooddonation.entity.*;
import com.gachonsw.blooddonation.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, UriComponentsBuilder b) {
        Long postId = postService.createPost(postRequestDto);

        UriComponents uriComponents =
                b.path("/posts/{postId}").buildAndExpand(postId);

        PostResponseDto res = new PostResponseDto(postService.findById(postId));
        return ResponseEntity.created(uriComponents.toUri()).body(res);
    }

    @GetMapping("/{postId}")
    public Result<PostAndAssociationDto> getPost(@PathVariable Long postId) {
        Post post = postService.findById(postId);

        PostAndAssociationDto res =  new PostAndAssociationDto(
                new AssociationResponseDto(post.getAssociation()), new PostResponseDto(post));

        return new Result<>(res);
    }

    @GetMapping("/users/{userId}")
    public Result<List<PostAndAssociationDto>> getUserPostList(@PathVariable Long userId) {
//        List<PostResponseDto> res = postService.findListByUser().stream()
//                .map(PostResponseDto::new)
//                .collect(Collectors.toList());

        List<PostAndAssociationDto> res = postService.findListByUser(userId).stream()
                .map(p -> new PostAndAssociationDto(
                        new AssociationResponseDto(p.getAssociation()), new PostResponseDto(p)
                ))
                .collect(Collectors.toList());

        return new Result<>(res);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePostAndRelated(postId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Result<PostsInAssociationDto> getPostsByAssociation(@RequestParam Long associationId, Pageable pageable) {
        Slice<PostResponseDto> map = postService.findSliceWithPostByAssociation(associationId, pageable)
                .map(PostResponseDto::new);

        PostsInAssociationDto res = new PostsInAssociationDto(associationId, map);
        return new Result<>(res);
    }


//    //paging
//    @GetMapping("/{associationId}")
//    public Result<PostAssociationResponseDto> getPostsByAssociation(@PathVariable Long associationId){
//        postService.getPostsByAssociation(associationId);
////
////        Association association = associationService.findById(associationId);
////        List<PostAssociation> listByAssociation = postAssociationService.findListByAssociation(association);
////
////        List<Long> postIdList = listByAssociation.stream()
////                .map(m->m.getPost().getId())
////                .collect(Collectors.toList());
////        PostAssociationResponseDto result = new PostAssociationResponseDto(association.getId(), association.getName(), postIdList);
//
//        return new Result(result);
//    }

}
