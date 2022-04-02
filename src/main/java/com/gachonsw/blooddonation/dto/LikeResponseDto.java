package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.Liked;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponseDto {

    private Long likedId;
    private UserResponseDto userFrom;
    private UserResponseDto userTo;

    public LikeResponseDto(Liked liked){
        this.likedId = liked.getId();
        this.userFrom = new UserResponseDto(liked.getFromUser());
        this.userTo = new UserResponseDto(liked.getToUser());
    }
}
