package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Set;

@NoRepositoryBean
public interface ByUserIdRepo<T, ID, UserId> extends JpaRepository<T, ID> {
    List<T> findByUserId(UserId userId);
}
