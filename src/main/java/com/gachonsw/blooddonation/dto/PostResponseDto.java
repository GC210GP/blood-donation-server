package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private Long userId;
    private String userNickname;
//
//    public Post toEntityExceptUser(PostResponseDto postResponseDto){
//        return Post.builder()
//                .title(postResponseDto.getTitle())
//                .content(postResponseDto.getContent())
//                .isActive(postResponseDto.getIsActive())
//                .build();
//    }

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.isActive = post.getIsActive();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();

        this.userId = post.getUser().getId();
        this.userNickname = post.getUser().getNickname();
    }

}
