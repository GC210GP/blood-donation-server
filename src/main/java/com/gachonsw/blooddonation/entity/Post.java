package com.gachonsw.blooddonation.entity;

import com.gachonsw.blooddonation.dto.PostDto;
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

    public void update(PostDto postDto) {

//        if (postDto.getUser() != null)
//            this.user = postDto.getUser();
        if (postDto.getTitle() != null)
            this.title = postDto.getTitle();
        if (postDto.getContent() != null)
            this.content = postDto.getContent();
        if (postDto.getIsActive() != null)
            this.isActive = postDto.getIsActive();

    }
}
