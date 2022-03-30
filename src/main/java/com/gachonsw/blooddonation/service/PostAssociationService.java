package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.entity.*;
import com.gachonsw.blooddonation.repository.PostAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostAssociationService {

    private final PostAssociationRepository postAssociationRepository;

    private final UserAssociationService userAssociationService;

    @Transactional
    public void createPostAssociations(Post post){
        List<UserAssociation> listByUserId = userAssociationService.findListWithAssociationByUser();

        for (UserAssociation ua : listByUserId) {
            PostAssociation postAssociation = PostAssociation.builder()
                    .post(post)
                    .association(ua.getAssociation())
                    .build();
            postAssociationRepository.save(postAssociation);
        }
    }

    @Transactional
    public void deleteAllByPost(Post post){
        postAssociationRepository.deleteAllByPost(post);
    }

    public List<PostAssociation> findListByAssociation(Association association){
        return postAssociationRepository.findWithPostByAssociation(association);
    }

    public Slice<PostAssociation> findSliceWithPostByAssociation(Association association, Pageable pageable){
        return postAssociationRepository.findSliceWithPostByAssociation(association, pageable);
    }
}
