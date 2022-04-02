package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.Liked;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLikedResponseDto {

    private UserResponseDto userFrom;
    private List<LikedAndUserToDto> likedInfo = new ArrayList<>();

    public UserLikedResponseDto(List<Liked> likedList){
        for (Liked l : likedList) {
            this.userFrom = new UserResponseDto(l.getFromUser());
            this.likedInfo.add(new LikedAndUserToDto(l));
        }
    }

}
