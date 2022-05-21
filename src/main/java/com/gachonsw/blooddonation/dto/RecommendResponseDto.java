package com.gachonsw.blooddonation.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecommendResponseDto {

    private List<Long> result;    //user Id
    private List<Double> weight;

}
