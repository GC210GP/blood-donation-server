package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.PostDto;
import com.gachonsw.blooddonation.entity.*;
import com.gachonsw.blooddonation.repository.PostAssociationRepository;
import com.gachonsw.blooddonation.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostAssociationService {

    private final PostAssociationRepository postAssociationRepository;
    private final PostRepository postRepository;
    private final UserAssociationService userAssociationService;

    @Transactional
    public void createPostAssociations(PostDto postDto, Long postId){
        List<UserAssociation> listByUserId = userAssociationService.findListByUserId(postDto.getUserId());
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 포스트입니다."));

        for (UserAssociation ua : listByUserId) {
            PostAssociation postAssociation = PostAssociation.builder()
                    .post(post)
                    .association(ua.getAssociation())
                    .build();
            postAssociationRepository.save(postAssociation);
        }
    }

    @Transactional
    public void deletePostAssociations(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 포스트입니다."));
        postAssociationRepository.deleteAllByPost(post);
    }

    public List<PostAssociation> findListByAssociation(Association association){
        return postAssociationRepository.findByAssociation(association);
    }
}
