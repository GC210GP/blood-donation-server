package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}
