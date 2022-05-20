package com.gachonsw.blooddonation.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecommendRequestDto {

    private Long userId;
    private List<Long> likedList;
}
