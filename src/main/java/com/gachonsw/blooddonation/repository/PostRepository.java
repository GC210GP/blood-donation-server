package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
