package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.BloodType;
import com.gachonsw.blooddonation.entity.Sex;
import com.gachonsw.blooddonation.entity.UserAuthority;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserUpdateDto {

    private Long id;
    private String name;
    private String password;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String profileImageLocation;
    private LocalDate birthdate;
    private String location;
    private Sex sex;
    private String job;
    private BloodType bloodType;
    private Boolean isDormant;
    private LocalDate recency;
    private Long frequency;
    private Boolean isDonated;

//    private List<UserAuthority> userAuthorityList  = new ArrayList<>();

}
