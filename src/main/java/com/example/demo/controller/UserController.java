package com.example.demo.controller;


import ca.bitcoco.jsk.http.HttpResponseBody;
import com.example.demo.domain.*;
import com.example.demo.dtos.UserDto;
import com.example.demo.repo.*;
import com.example.demo.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.UUID;


@RestController

public class UserController {
    private final ModelMapper mapper = new ModelMapper();

    private final UserRepo repo;
    private final HomeworkRepo homeworkRepo;
    private final ProjectRepo projectRepo;
    private final LessonHomeworkRepo lessonHomeworkRepo;
    private final CourseRepo courseRepo;
    private final LessonRepo lessonRepo;

    private final CourseService courseService;

    public UserController(UserRepo repo, HomeworkRepo homeworkRepo, ProjectRepo projectRepo, LessonHomeworkRepo lessonHomeworkRepo, CourseRepo courseRepo, LessonRepo lessonRepo, CourseService courseService) {
        this.repo = repo;
        this.homeworkRepo = homeworkRepo;
        this.projectRepo = projectRepo;
        this.lessonHomeworkRepo = lessonHomeworkRepo;

        this.courseRepo = courseRepo;
        this.lessonRepo = lessonRepo;
        this.courseService = courseService;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<User> createOne(@Valid @RequestBody UserDto request, Error error, @PathParam("page") Integer page) {
        User user = mapper.map(request, User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(repo.save(user));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpResponseBody> getAll(Authentication auth, Pageable pageable) {
        try{
            return new ResponseEntity<>(HttpResponseBody.result(repo.findAll(pageable)), HttpStatus.OK);
        }
        catch (Exception ex){
            return null;
        }
    }

    @GetMapping("/users/one")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> getOne(Authentication auth) {
        ModelMapper mapper = new ModelMapper();
        User user1 = mapper.map(auth.getPrincipal(), User.class);
        User user = (User)auth.getPrincipal();
        System.out.println(user.getEmail());
        return ResponseEntity.ok(repo.findById(34L));
    }


    @GetMapping("/test")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> test(Authentication auth) {
        Course course = new Course();
        course.setLabel("course 2");
        courseRepo.save(course);

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setLabel("lesson 2");
        lessonRepo.save(lesson);

        Project project = new Project();
        project.setLabel("project 2");
        project.setCourse(course);
        project.setTotal(10d);
        project.setWeight(0.2d);
        projectRepo.save(project);

        LessonHomework lessonHomework = new LessonHomework();
        lessonHomework.setLesson(lesson);
        lessonHomework.setCourse(course);
        lessonHomework.setLabel("lessonhome work2");
        lessonHomework.setTotal(10d);
        lessonHomework.setWeight(0.2d);
        lessonHomeworkRepo.saveAndFlush(lessonHomework);

        return ResponseEntity.ok(homeworkRepo.findAll());
    }


    @GetMapping("/getAllLessonHomework")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> getAllLessonHomework(Authentication auth) {
        Lesson lesson = lessonRepo.findById(2l).orElse(null);
        return ResponseEntity.ok(lessonHomeworkRepo.findAllByLesson(lesson));
    }

    @GetMapping("/getAllHomeworks")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> getAllHomeworks(Authentication auth) {
        Lesson lesson = lessonRepo.findById(2l).orElse(null);
        return ResponseEntity.ok(homeworkRepo.findAll());
    }

    @GetMapping("/saveTest")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> saveTest(Authentication auth) {
        Course course = new Course();
        course.setLabel("course " + UUID.randomUUID().toString());
        courseService.save(course);
        return ResponseEntity.ok(courseService.getList());
    }


}


