package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.PostAssociationResponseDto;
import com.gachonsw.blooddonation.dto.PostDto;
import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.entity.*;
import com.gachonsw.blooddonation.repository.AssociationRepository;
import com.gachonsw.blooddonation.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostAssociationService postAssociationService;
    private final UserAssociationService userAssociationService;
    private final UserService userService;
    private final AssociationService associationService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, UriComponentsBuilder b) {
        Long postId = postService.createPost(postDto);
        postAssociationService.createPostAssociations(postDto,postId);

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
        postAssociationService.deletePostAssociations(postId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto){
        postService.updatePost(postId,postDto);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{associationId}")
    public Result<PostAssociationResponseDto> getPostsByAssociation(@PathVariable Long associationId){
        Association association = associationService.findById(associationId);
        List<PostAssociation> listByAssociation = postAssociationService.findListByAssociation(association);

        List<Long> postIdList = listByAssociation.stream()
                .map(m->m.getPost().getId())
                .collect(Collectors.toList());
        PostAssociationResponseDto result = new PostAssociationResponseDto(association.getId(), association.getName(), postIdList);

        return new Result(result);
    }

}
