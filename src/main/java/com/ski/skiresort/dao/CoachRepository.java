package com.ski.skiresort.dao;

import com.ski.skiresort.domain.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByFullName(String fullName);
}
