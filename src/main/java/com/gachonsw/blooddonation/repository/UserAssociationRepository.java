package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.entity.UserAssociation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAssociationRepository extends JpaRepository<UserAssociation, Long> {

    @EntityGraph(attributePaths = "association")
    List<UserAssociation> findListWithAssociationByUser(User user);

    @EntityGraph(attributePaths = "association")
    Optional<UserAssociation> findWithAssociationById(Long id);
}
