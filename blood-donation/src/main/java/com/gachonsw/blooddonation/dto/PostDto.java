package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.Post;
import com.gachonsw.blooddonation.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;
    private Long userId;
//    private Long associationId;

    //    private User user;
    private String title;
    private String content;
    private Boolean isActive;

    public Post toEntityExceptUser(PostDto postDto){
        return Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .isActive(postDto.getIsActive())
                .build();
    }

    public PostDto (Post post){
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.isActive = post.getIsActive();
    }

}
