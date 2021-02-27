package com.example.demo.repo;

import com.example.demo.domain.AssignmentResult;
import com.example.demo.domain.HomeworkResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentHomeworkResultRepo extends HomeworkResultRepo<AssignmentResult> {

}
