package com.gachonsw.blooddonation.entity;

import com.gachonsw.blooddonation.dto.UserUpdateDto;
import com.gachonsw.blooddonation.entity.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String password;

    @Column(name = "user_name")
    private String name;

    private String nickname;
    private String email;
    private String phoneNumber;
    private String profileImageLocation;
    private LocalDate birthdate;
    private String location;
    private LocalDate recency;
    private Long frequency;
    private Boolean isDonated;

    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    private String job;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Builder.Default
    private Boolean isDormant = Boolean.FALSE;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserAuthority> userAuthorityList = new ArrayList<>();

    private String emailValidCode;

    private String fbToken;


//    @OneToMany(mappedBy = "user")
//    private List<UserAuthority> userAuthorityList = new ArrayList<>();

//    public void changeUserAuthorityList(List<UserAuthority> userAuthorityList){
//        this.userAuthorityList = userAuthorityList;
//    }


    public void update(UserUpdateDto userUpdateDto) {
        if (userUpdateDto.getId() != null)
            this.id = userUpdateDto.getId();
        if (userUpdateDto.getName() != null)
            this.name = userUpdateDto.getName();
        if (userUpdateDto.getNickname() != null)
            this.nickname = userUpdateDto.getNickname();
        if (userUpdateDto.getEmail() != null)
            this.email = userUpdateDto.getEmail();
        if (userUpdateDto.getPhoneNumber() != null)
            this.phoneNumber = userUpdateDto.getPhoneNumber();
        if (userUpdateDto.getProfileImageLocation() != null)
            this.profileImageLocation = userUpdateDto.getProfileImageLocation();
        if (userUpdateDto.getBirthdate() != null)
            this.birthdate = userUpdateDto.getBirthdate();
        if (userUpdateDto.getLocation() != null)
            this.location = userUpdateDto.getLocation();
        if (userUpdateDto.getSex() != null)
            this.sex = userUpdateDto.getSex();
        if (userUpdateDto.getJob() != null)
            this.job = userUpdateDto.getJob();
        if (userUpdateDto.getBloodType() != null)
            this.bloodType = userUpdateDto.getBloodType();
        if (userUpdateDto.getIsDormant() != null)
            this.isDormant = userUpdateDto.getIsDormant();
        if (userUpdateDto.getRecency() != null)
            this.recency = userUpdateDto.getRecency();
        if (userUpdateDto.getFrequency() != null)
            this.frequency = userUpdateDto.getFrequency();
        if (userUpdateDto.getIsDonated() != null)
            this.isDonated = userUpdateDto.getIsDonated();
        if(userUpdateDto.getFbToken()!= null)
            this.fbToken = userUpdateDto.getFbToken();

//
//        this.genres.clear();
//
//        for (String s : userRequest.getGenres()) {
//            Genre genre = Genre.builder()
//                    .type(s)
//                    .build();
//            genre.addUser(this);
//        }
//
//        this.userAuthorityList = userRequestDto.getUserAuthorityList();
    }

    public void changeDormant(){
         this.isDormant = !this.isDormant;
    }
    public void changePw(String password){this.password = password;}
    public void changeEmailValidCode(String emailValidCode) {
        this.emailValidCode = emailValidCode;
    }

    public void changeIsDormant() {
        if (isDormant) {
            this.isDormant = Boolean.FALSE;
            return;
        }
        this.isDormant = Boolean.TRUE;
    }
}
