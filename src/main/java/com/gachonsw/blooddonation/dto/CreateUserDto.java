package com.gachonsw.blooddonation.dto;

import com.gachonsw.blooddonation.entity.BloodType;
import com.gachonsw.blooddonation.entity.Sex;
import com.gachonsw.blooddonation.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateUserDto {

    private String name;
    private String nickname;
    private String email;
    private String password;
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

//    private List<UserAssociationDto> userAssociationDtoList;

    public User toEntity(CreateUserDto createUserDto){
        return User.builder()
                .name(createUserDto.name)
                .nickname(createUserDto.nickname)
                .email(createUserDto.email)
                .password(createUserDto.password)
                .phoneNumber(createUserDto.phoneNumber)
                .profileImageLocation(createUserDto.profileImageLocation)
                .birthdate(createUserDto.birthdate)
                .location(createUserDto.location)
                .sex(createUserDto.sex)
                .job(createUserDto.job)
                .bloodType(createUserDto.bloodType)
                .recency(createUserDto.recency)
                .frequency(createUserDto.frequency)
                .isDonated(createUserDto.isDonated)
                .isDormant(createUserDto.isDormant)
                .fbToken((createUserDto.fbToken))
                .build();
    }

//    public User toEntityExceptPw(CreateUserDto createUserDto){
//        return User.builder()
//                .name(createUserDto.name)
//                .nickname(createUserDto.nickname)
//                .email(createUserDto.email)
//                .phoneNumber(createUserDto.phoneNumber)
//                .profileImageLocation(createUserDto.profileImageLocation)
//                .birthdate(createUserDto.birthdate)
//                .location(createUserDto.location)
//                .sex(createUserDto.sex)
//                .job(createUserDto.job)
//                .bloodType(createUserDto.bloodType)
//                .recency(createUserDto.recency)
//                .frequency(createUserDto.frequency)
//                .isDonated(createUserDto.isDonated)
//                .isDormant(createUserDto.isDormant)
//                .build();
//    }

}
