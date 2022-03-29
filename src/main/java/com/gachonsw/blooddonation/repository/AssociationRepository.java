package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssociationRepository extends JpaRepository<Association, Long> {

    Optional<Association> findByName(String name);

    List<Association> findByNameContaining(String name);
}
