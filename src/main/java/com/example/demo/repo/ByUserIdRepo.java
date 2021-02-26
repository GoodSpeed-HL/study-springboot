package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ByUserIdRepo<T, ID, UserId> extends JpaRepository<T, ID> {

}
