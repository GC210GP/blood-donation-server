package com.gachonsw.blooddonation.repository;

import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.entity.UserSns;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSnsRepository extends JpaRepository<UserSns, Long> {

    @EntityGraph(attributePaths = "sns")
    List<UserSns> findListByUser(User user);
}
