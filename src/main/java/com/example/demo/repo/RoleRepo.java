package com.example.demo.repo;

import com.example.demo.domain.Role;
import com.example.demo.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByLabel(RoleType type);

}
