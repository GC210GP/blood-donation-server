package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.entity.Association;
import com.gachonsw.blooddonation.repository.AssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AssociationService {

    private final AssociationRepository associationRepository;

    public Association findById(Long associationId){
        return associationRepository.findById(associationId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 association입니다."));
    }

}
