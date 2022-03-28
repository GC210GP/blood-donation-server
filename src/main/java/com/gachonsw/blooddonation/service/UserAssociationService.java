package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.UserAssociationDto;
import com.gachonsw.blooddonation.entity.Association;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.entity.UserAssociation;
import com.gachonsw.blooddonation.repository.AssociationRepository;
import com.gachonsw.blooddonation.repository.UserAssociationRepository;
import com.gachonsw.blooddonation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAssociationService {

    private final UserAssociationRepository userAssociationRepository;
    private final AssociationRepository associationRepository;
//    private final UserRepository userRepository;
    private final UserService userService;

//    @Transactional
//    public Long createUserAssociation(CreateUserAssociationDto createUserAssociationDto){
//        Association.builder()
//                .name(createUserAssociationDto.getAssociationName())
//                .build()
//        UserAssociation.builder()
//                .user(user)
//                .ass
//                .build()
//    }

    //UserAssociation
    @Transactional
    public Long createUserAssociation(UserAssociationDto userAssociationDto){
//        User user = userRepository.findById(userAssociationDto.getUserId()).orElseThrow(()-> new EntityNotFoundException("존재하지 않는 유저입니다."));
        User user = userService.findById(userAssociationDto.getUserId());

        Optional<Association> associationByName = associationRepository.findByName(userAssociationDto.getAssociationName());

        Association foundOrCreated = findOrCreateAssociation(userAssociationDto, associationByName);

        UserAssociation userAssociation = UserAssociation.builder()
                .user(user)
                .association(foundOrCreated)
                .build();

        UserAssociation result = userAssociationRepository.save(userAssociation);

        return result.getId();
    }

    private Association findOrCreateAssociation(UserAssociationDto userAssociationDto, Optional<Association> associationByName) {
        Association foundOrCreated;
        if (associationByName.isPresent())
            foundOrCreated = associationByName.get();
        else{
            Association association = Association.builder()
                    .name(userAssociationDto.getAssociationName())
                    .build();
            associationRepository.save(association);
            foundOrCreated = association;
        }
        return foundOrCreated;
    }

    //Association
    public Optional<Association> findAssociationByName(String associationName){
        return associationRepository.findByName(associationName);
    }

    @Transactional
    public Long createAssociation(Association association){
        Association result = associationRepository.save(association);
        return result.getId();
    }

    public List<UserAssociation> findListByUserId(Long userId){
//        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("존재하지 않는 유저입니다."));
        User user = userService.findById(userId);

        return userAssociationRepository.findListByUser(user);
    }

    @Transactional
    public void deleteById(Long userAssociationID){
        userAssociationRepository.deleteById(userAssociationID);
    }




}
