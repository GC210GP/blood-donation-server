package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.Association;
import com.gachonsw.blooddonation.entity.Post;
import com.gachonsw.blooddonation.entity.PostAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostAssociationRepository extends JpaRepository<PostAssociation, Long> {

    void deleteAllByPost(Post post);
    List<PostAssociation> findByAssociation(Association association);
}
