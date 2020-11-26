package com.serverhealthcheck.health.repository;

import com.serverhealthcheck.health.entity.UserTG;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTG, Long> {
}
