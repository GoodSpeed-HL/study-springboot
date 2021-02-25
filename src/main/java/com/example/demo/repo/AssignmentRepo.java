package com.example.demo.repo;

import com.example.demo.domain.LessonHomework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepo extends JpaRepository<LessonHomework, Long> {

}
