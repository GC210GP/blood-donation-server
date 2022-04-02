package com.gachonsw.blooddonation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLikeDto {

    private Long userIdFrom;
    private Long userIdTo;
}
