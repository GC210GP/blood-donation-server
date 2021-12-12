package com.gachonsw.blooddonation.entity;

import com.gachonsw.blooddonation.entity.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Association extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "association_id")
    private Long id;

    @Column(name = "association_name")
    private String name;
}
