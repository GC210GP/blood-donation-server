package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.CreateAssociationDto;
import com.gachonsw.blooddonation.entity.Association;
import com.gachonsw.blooddonation.repository.AssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AssociationService {

    private final AssociationRepository associationRepository;

    public Association findById(Long associationId){
        return associationRepository.findById(associationId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 association입니다."));
    }

    @Transactional
    public Long createAssociation(CreateAssociationDto createAssociationDto){
        validateDuplicateAssociation(createAssociationDto);

        Association association = Association.builder()
                .name(createAssociationDto.getAssociationName())
                .build();
        associationRepository.save(association);

        return association.getId();
    }

    private void validateDuplicateAssociation(CreateAssociationDto createAssociationDto) {
        boolean exists = associationRepository.existsByName(createAssociationDto.getAssociationName());
        if (exists)
            throw new IllegalStateException("이미 존재하는 association 입니다.");
    }

    //쿼리 포함하는 문자열 기준
    public List<Association> searchAssociationNameLike(String query){
        return associationRepository.findByNameContaining(query);
    }

//    //어드민 기능으로 추후 개발
//    @Transactional
//    public void deleteWithRelated(Long associationId){
//        associationRepository.deleteById(associationId);
//    }



}
