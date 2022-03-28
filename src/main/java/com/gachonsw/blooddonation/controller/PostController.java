package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.PostAssociationResponseDto;
import com.gachonsw.blooddonation.dto.PostDto;
import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.entity.*;
import com.gachonsw.blooddonation.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

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
        Post post = postService.findById(postId);

        PostDto postDto = new PostDto(post);
        return new Result<>(postDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        postService.deletePostAndRelated(postId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto){
        postService.updatePost(postId,postDto);
        return ResponseEntity.noContent().build();
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
