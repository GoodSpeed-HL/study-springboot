package com.example.demo.repo;

import com.example.demo.domain.HomeworkResult;
import com.example.demo.domain.Lesson;
import com.example.demo.domain.LessonHomework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface HomeworkResultRepo<T extends HomeworkResult> extends JpaRepository<T, Long>, ByUserIdRepo<T, Long, Long> {

}
