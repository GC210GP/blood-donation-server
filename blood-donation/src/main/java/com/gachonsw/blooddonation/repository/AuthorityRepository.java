package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
}
