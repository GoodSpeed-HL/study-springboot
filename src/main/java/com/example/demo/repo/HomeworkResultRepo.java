package com.example.demo.repo;

import com.example.demo.domain.HomeworkResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkResultRepo<T extends HomeworkResult> extends JpaRepository<T, Long>, ByUserIdRepo<T, Long, Long> {

}
