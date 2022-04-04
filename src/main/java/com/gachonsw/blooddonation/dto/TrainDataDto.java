package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.BloodType;
import com.gachonsw.blooddonation.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDataDto {
    private Long userId;
    private List<Long> likedList = new ArrayList<>();
}
