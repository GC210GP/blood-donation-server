package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.PostRequestDto;
import com.gachonsw.blooddonation.entity.*;
import com.gachonsw.blooddonation.repository.PostRepository;
import com.gachonsw.blooddonation.repository.UserAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserAssociationRepository userAssociationRepository;

    private final UserService userService;
    private final PostAssociationService postAssoService;
    private final AssociationService associationService;

    @Transactional
    public Long createPost(PostRequestDto postRequestDto){
        Post post = postRequestDto.toEntityExceptUser(postRequestDto);
        User user = userService.findUserFromToken();
        post.changeUser(user);
        postRepository.save(post);

        createPostAssociations(post,user);

        return post.getId();
    }

    private void createPostAssociations(Post post, User user) {
        List<UserAssociation> listByUserId = userAssociationRepository.findListWithAssociationByUser(user);
        for (UserAssociation ua : listByUserId) {
            PostAssociation pa = PostAssociation.builder()
                    .post(post)
                    .association(ua.getAssociation())
                    .build();
            postAssoService.createPostAssociation(pa);
        }
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto){
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 포스트입니다."));
        post.update(postRequestDto);
    }

    @Transactional
    public void deletePostAndRelated(Long postId){
        Post post = findById(postId);
        postAssoService.deleteAllByPost(post);
        postRepository.delete(post);
    }

    public Post findById(Long id){
        return postRepository.findById(id).orElseThrow(()->  new EntityNotFoundException("존재하지 않는 포스트입니다."));
    }

    @Transactional
    public void deleteAll(List<Post> posts){
        postRepository.deleteAll(posts);
    }

    //paging
    public Slice<Post> findSliceWithPostByAssociation(Long associationId, Pageable pageable) {
        Association association = associationService.findById(associationId);
        return postAssoService.findSliceWithPostByAssociation(association, pageable).map(PostAssociation::getPost);
    }
}
