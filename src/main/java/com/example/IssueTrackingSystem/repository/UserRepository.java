package com.example.IssueTrackingSystem.repository;


import com.example.IssueTrackingSystem.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    List<User> findAllByUserNameContainingIgnoreCaseOrderByCreatedAtDesc(String userName);

    List<User> findAllByOrderByCreatedAtDesc();
}


