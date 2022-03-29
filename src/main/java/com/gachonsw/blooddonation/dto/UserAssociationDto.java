package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.UserAssociation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAssociationDto {

    private Long userAssociationId;
    private Long associationId;
    private String associationName;

    public UserAssociationDto(UserAssociation userAssociation){
        this.userAssociationId = userAssociation.getId();
        this.associationId = userAssociation.getAssociation().getId();
        this.associationName = userAssociation.getAssociation().getName();
    }

}
