package com.gachonsw.blooddonation.entity;

import com.gachonsw.blooddonation.entity.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSns extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_sns_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sns_id")
    private Sns sns;

    @Column(name = "sns_profile")
    private String profile;
}
