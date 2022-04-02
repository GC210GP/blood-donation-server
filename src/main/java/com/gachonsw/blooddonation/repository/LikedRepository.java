package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.Liked;
import com.gachonsw.blooddonation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedRepository extends JpaRepository<Liked, Long> {

    List<Liked> findByFromUser(User fromUser);
}
