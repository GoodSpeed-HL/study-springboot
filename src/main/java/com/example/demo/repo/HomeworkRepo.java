package com.example.demo.repo;

import com.example.demo.domain.Course;
import com.example.demo.domain.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface HomeworkRepo extends JpaRepository<Homework, Long> {
    Set<Homework> findAllByCourse(Course course);
}
