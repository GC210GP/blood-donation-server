package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.PostAssociation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PostAssociationResponseDto {
    private Long associationId;
    private String associationName;
    private List<Long> postIdList;
}
