package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.CreateSnsDto;
import com.gachonsw.blooddonation.entity.Sns;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.entity.UserSns;
import com.gachonsw.blooddonation.repository.SnsRepository;
import com.gachonsw.blooddonation.repository.UserSnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSnsService {

    private final SnsRepository snsRepository;
    private final UserSnsRepository userSnsRepository;

    private final UserService userService;

    @Transactional
    public Long createUserSns(CreateSnsDto createSnsDto){
        User user = userService.findById(createSnsDto.getUserId());
        Sns sns = snsRepository.findByType(createSnsDto.getSnsType());

        UserSns userSns = UserSns.builder()
                .user(user)
                .sns(sns)
                .profile(createSnsDto.getSnsProfile())
                .build();
        UserSns save = userSnsRepository.save(userSns);

        return save.getId();
    }

    public List<UserSns> findListByUser(User user){
        return userSnsRepository.findListByUser(user);
    }

    @Transactional
    public void deleteUserSns(Long userSnsId){
        userSnsRepository.deleteById(userSnsId);
    }
}
