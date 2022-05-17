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
    private final AssociationService associationService;

    @Transactional
    public Long createPost(PostRequestDto postRequestDto){
        Post post = postRequestDto.toEntityExceptUserAndAssociation(postRequestDto);

        User user = userService.findUserFromToken();
        post.changeUser(user);

        Association association = associationService.findById(postRequestDto.getAssociationId());
        post.changeAssociation(association);

        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto){
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 포스트입니다."));
        post.update(postRequestDto);
    }

    @Transactional
    public void deletePostAndRelated(Long postId){
        Post post = findById(postId);
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

        return postRepository.findSliceWithAssociationByAssociation(association, pageable);
//        return postAssoService.findSliceWithPostByAssociation(association, pageable).map(PostAssociation::getPost);
    }

    public List<Post> findListByUser(Long userId){
        User user = userService.findById(userId);
        return postRepository.findListByUser(user);
    }


}
