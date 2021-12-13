package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.Sns;
import com.gachonsw.blooddonation.entity.SnsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsRepository extends JpaRepository<Sns, Long> {

    Sns findByType(SnsType type);
}
