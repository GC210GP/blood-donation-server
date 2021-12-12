package com.gachonsw.blooddonation.dto;


import lombok.Data;

@Data
public class CreateUserAssociationDto {

    private String associationName;
    private Long userId;
}
