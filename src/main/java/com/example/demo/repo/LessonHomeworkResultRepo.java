package com.example.demo.repo;

import com.example.demo.domain.AssignmentResult;
import com.example.demo.domain.HomeworkResult;
import com.example.demo.domain.LessonHomeworkResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonHomeworkResultRepo extends HomeworkResultRepo<LessonHomeworkResult> {

}
