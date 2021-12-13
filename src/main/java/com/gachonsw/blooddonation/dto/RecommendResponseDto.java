package com.gachonsw.blooddonation.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecommendResponseDto {

    private List<Long> userId = new ArrayList<>();
}
