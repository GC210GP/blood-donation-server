package com.gachonsw.blooddonation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAssociationDto {

    private Long userId;
    private Long userAssociationId;
    private Long associationId;
    private String associationName;

}
