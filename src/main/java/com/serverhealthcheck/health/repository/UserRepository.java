package com.serverhealthcheck.health.repository;

import com.serverhealthcheck.health.entity.UserTG;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserTG, Long> {
    Optional<UserTG> findByName(String name);
}
