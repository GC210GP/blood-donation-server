package com.gachonsw.blooddonation.entity;

import com.gachonsw.blooddonation.entity.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sns extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "sns_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private SnsType type;
}
