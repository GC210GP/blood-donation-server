package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.entity.UserAssociation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAssociationRepository extends JpaRepository<UserAssociation, Long> {

    @EntityGraph(attributePaths = "association")
    List<UserAssociation> findListByUser(User user);
}
