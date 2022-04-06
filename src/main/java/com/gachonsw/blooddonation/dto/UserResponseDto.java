package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String profileImageLocation;
    private LocalDate birthdate;
    private String location;
    private Sex sex;
    private String job;
    private BloodType bloodType;
    private LocalDate recency;
    private Long frequency;
    private Boolean isDonated;
    private Boolean isDormant;
    private String fbToken;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

//    private BloodDonateResponseDto bloodDonateResponseDto;
//    private List<UserAssociationDto> userAssociationDtoList;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.profileImageLocation = user.getProfileImageLocation();
        this.birthdate = user.getBirthdate();
        this.location = user.getLocation();
        this.sex = user.getSex();
        this.job = user.getJob();
        this.bloodType = user.getBloodType();
        this.isDormant = user.getIsDormant();
        this.createdDate = user.getCreatedDate();
        this.modifiedDate = user.getModifiedDate();
        this.recency = user.getRecency();
        this.frequency = user.getFrequency();
        this.isDonated = user.getIsDonated();
        this.fbToken = user.getFbToken();

//        this.bloodDonateResponseDto = new BloodDonateResponseDto(user.getBloodDonate());


    }
}
