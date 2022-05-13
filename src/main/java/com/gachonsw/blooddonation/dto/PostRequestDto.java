package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private Boolean isActiveGiver;
    private Boolean isActiveReceiver;

    private Long associationId;

    public Post toEntityExceptUserAndAssociation(PostRequestDto postRequestDto){
        return Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .isActiveGiver(postRequestDto.getIsActiveGiver())
                .isActiveReceiver(postRequestDto.getIsActiveReceiver())
                .build();
    }

}