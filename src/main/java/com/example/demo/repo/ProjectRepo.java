package com.example.demo.repo;

import com.example.demo.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
