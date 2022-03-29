package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.Association;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationResponseDto {
    private Long id;
    private String associationName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public AssociationResponseDto(Association association){
        this.id = association.getId();
        this.associationName = association.getName();
        this.createdDate = association.getCreatedDate();
        this.modifiedDate = association.getModifiedDate();
    }

}
