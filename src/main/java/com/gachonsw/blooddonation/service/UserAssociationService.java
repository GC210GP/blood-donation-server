package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.CreateUserAssociationDto;
import com.gachonsw.blooddonation.entity.*;
import com.gachonsw.blooddonation.repository.PostAssociationRepository;
import com.gachonsw.blooddonation.repository.PostRepository;
import com.gachonsw.blooddonation.repository.UserAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAssociationService {

    private final UserAssociationRepository userAssociationRepository;
    private final PostRepository postRepository;
    private final PostAssociationRepository postAssociationRepository;

    private final AssociationService assoService;
    private final UserService userService;


    @Transactional
    public Long createUserAssociation(CreateUserAssociationDto createUserAssociationDto){
        User user = userService.findUserFromToken();
        Association association = assoService.findById(createUserAssociationDto.getAssociationId());

        UserAssociation userAssociation = UserAssociation.builder()
                .user(user)
                .association(association)
                .build();
        UserAssociation result = userAssociationRepository.save(userAssociation);

        createPostAssociations(association, user);

        return result.getId();
    }

    private void createPostAssociations(Association association, User user) {
        List<Post> postList = postRepository.findListByUser(user);
        for (Post post : postList){
            PostAssociation pa = PostAssociation.builder()
                    .post(post)
                    .association(association)
                    .build();
            postAssociationRepository.save(pa);
        }
    }

    public UserAssociation findWithAssociationById(Long userAssociationId){
        return userAssociationRepository.findWithAssociationById(userAssociationId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 UserAssociation입니다."));
    }

    public UserAssociation findById(Long userAssociationId){
        return userAssociationRepository.findById(userAssociationId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 UserAssociation입니다."));
    }


//    public List<Association> findAssoListByUser(){
////        User user = userService.findUserFromToken();
////
////        return userAssociationRepository.findListByUser(user).stream()
////                .map(UserAssociation::getAssociation)
////                .collect(Collectors.toList());
//
//
//    }

    public List<UserAssociation> findListWithAssociationByUser(){
        User user = userService.findUserFromToken();
        return userAssociationRepository.findListWithAssociationByUser(user);
    }

    @Transactional
    public void deleteById(Long userAssociationId){
        userAssociationRepository.deleteById(userAssociationId);
    }

    @Transactional
    public void deleteByIdWithRelated(Long userAssociationId){
        UserAssociation ua = findById(userAssociationId);
        userAssociationRepository.delete(ua);

        deletePostAssociations(ua);
    }

    private void deletePostAssociations(UserAssociation ua) {
        User user = userService.findUserFromToken();
        List<Post> postList = postRepository.findListByUser(user);
        for(Post post : postList){
            postAssociationRepository.deleteByPostAndAssociation(post, ua.getAssociation());
        }
    }


}
