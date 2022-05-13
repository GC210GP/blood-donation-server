package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.Association;
import com.gachonsw.blooddonation.entity.Post;
import com.gachonsw.blooddonation.entity.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = "association")
    List<Post> findListByUser(User user);

    @EntityGraph(attributePaths = "association")
    Slice<Post> findSliceByAssociation(Association association);

    void deleteAllByAssociation(Association association);
}
