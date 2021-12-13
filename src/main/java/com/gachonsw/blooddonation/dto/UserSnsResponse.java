package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.SnsType;
import com.gachonsw.blooddonation.entity.UserSns;
import lombok.Data;

@Data
public class UserSnsResponse {
    private Long userSnsId;
    private SnsType snsType;
    private String profile;

    public UserSnsResponse(UserSns userSns){
        this.userSnsId = userSns.getId();
        this.snsType = userSns.getSns().getType();
        this.profile = userSns.getProfile();
    }
}
