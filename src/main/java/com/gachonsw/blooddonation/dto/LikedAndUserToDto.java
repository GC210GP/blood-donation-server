package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.Liked;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikedAndUserToDto {

    private Long likedId;
    private UserResponseDto userTo;

    public LikedAndUserToDto(Liked liked){
        this.likedId = liked.getId();
        this.userTo = new UserResponseDto(liked.getToUser());
    }
}
