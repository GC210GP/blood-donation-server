package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.Post;
import com.gachonsw.blooddonation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findListByUser(User user);
}
