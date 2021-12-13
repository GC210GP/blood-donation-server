package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.SnsType;
import lombok.Data;

@Data
public class CreateSnsDto {

    private Long userId;
    private SnsType snsType;
    private String snsProfile;
}
