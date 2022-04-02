package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.CreateLikeDto;
import com.gachonsw.blooddonation.entity.Liked;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.repository.LikedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikedService {

    private final LikedRepository likedRepository;

    private final UserService userService;

    @Transactional
    public Long createLike(CreateLikeDto createLikeDto){
        User from = userService.findById(createLikeDto.getUserIdFrom());
        User to = userService.findById(createLikeDto.getUserIdTo());

        Liked liked = Liked.builder()
                .fromUser(from)
                .toUser(to)
                .build();
        likedRepository.save(liked);

        return liked.getId();
    }

    public Liked findById(Long likeId){
        return likedRepository.findById(likeId).orElseThrow(()-> new EntityNotFoundException("존재하지 않는 Like입니다."));
    }

    @Transactional
    public void deleteLikeById(Long likeId){
        Liked liked = findById(likeId);
        likedRepository.delete(liked);
    }

    public List<Liked> findListByUser(Long userId) {
        User user = userService.findById(userId);
        return likedRepository.findByFromUser(user);
    }
}
