package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.repo.CourseRepo;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends CoreService<CourseRepo, Course, Long> {

    public CourseService(CourseRepo courseRepo) {
        super(courseRepo);
    }
}
