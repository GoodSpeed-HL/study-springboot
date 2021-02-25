package com.example.demo.repo;

import com.example.demo.domain.Lesson;
import com.example.demo.domain.LessonHomework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LessonHomeworkRepo extends JpaRepository<LessonHomework, Long> {
    Set<LessonHomework> findAllByLesson(Lesson lesson);
}
