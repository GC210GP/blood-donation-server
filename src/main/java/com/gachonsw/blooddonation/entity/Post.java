package com.gachonsw.blooddonation.entity;

import com.gachonsw.blooddonation.dto.PostRequestDto;
import com.gachonsw.blooddonation.dto.PostResponseDto;
import com.gachonsw.blooddonation.entity.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String content;
    private Boolean isActive;

    public void changeUser(User user){
        this.user = user;
    }

    public void update(PostRequestDto postRequestDto) {

//        if (postDto.getUser() != null)
//            this.user = postDto.getUser();
        if (postRequestDto.getTitle() != null)
            this.title = postRequestDto.getTitle();
        if (postRequestDto.getContent() != null)
            this.content = postRequestDto.getContent();
        if (postRequestDto.getIsActive() != null)
            this.isActive = postRequestDto.getIsActive();
    }
}
