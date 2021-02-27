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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private final HomeworkResultRepo homeworkResultRepo;
    private final LessonHomeworkResultRepo lessonHomeworkResultRepo;
    private final AssignmentHomeworkResultRepo assignmentHomeworkResultRepo;
    private final ProjectHomeworkResultRepo projectHomeworkResultRepo;
    private final CourseService courseService;


    public UserController(UserRepo repo, HomeworkRepo homeworkRepo, ProjectRepo projectRepo, LessonHomeworkRepo lessonHomeworkRepo, CourseRepo courseRepo, LessonRepo lessonRepo, HomeworkResultRepo homeworkResultRepo, LessonHomeworkResultRepo lessonHomeworkResultRepo, AssignmentHomeworkResultRepo assignmentHomeworkResultRepo, ProjectHomeworkResultRepo projectHomeworkResultRepo, CourseService courseService) {
        this.repo = repo;
        this.homeworkRepo = homeworkRepo;
        this.projectRepo = projectRepo;
        this.lessonHomeworkRepo = lessonHomeworkRepo;

        this.courseRepo = courseRepo;
        this.lessonRepo = lessonRepo;
        this.homeworkResultRepo = homeworkResultRepo;
        this.lessonHomeworkResultRepo = lessonHomeworkResultRepo;
        this.assignmentHomeworkResultRepo = assignmentHomeworkResultRepo;
        this.projectHomeworkResultRepo = projectHomeworkResultRepo;
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
        try {
            return new ResponseEntity<>(HttpResponseBody.result(repo.findAll(pageable)), HttpStatus.OK);
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/users/one")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> getOne(Authentication auth) {
        ModelMapper mapper = new ModelMapper();
        User user1 = mapper.map(auth.getPrincipal(), User.class);
        User user = (User) auth.getPrincipal();
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

    @GetMapping("/getAllLessons")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> getAllLessons(Authentication auth) {
        return ResponseEntity.ok(lessonRepo.findAll());
    }

    @GetMapping("/saveHomeWorkResult")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> saveHomeWorkResult(Authentication auth) {
        Homework assignmentHomework = homeworkRepo.findById(3L).orElse(null);
        Project projectHomework = projectRepo.findById(1L).orElse(null);
        LessonHomework lessonHomework = lessonHomeworkRepo.findById(2L).orElse(null);
        User user = repo.findById(35l).orElse(null);
        User user1 = repo.findById(34l).orElse(null);

        //save assignment result for user 35
        AssignmentResult result = new AssignmentResult();
        result.setScore(10d);
        result.setUserId(user.getId());
        result.setType("Assignment");
        result.setHomework(assignmentHomework);
        assignmentHomeworkResultRepo.saveAndFlush(result);

        //save project result for user 34
        ProjectResult result1 = new ProjectResult();
        result1.setScore(15d);
        result1.setUserId(user1.getId());
        result1.setType("Project");
        result1.setHomework(projectHomework);
        projectHomeworkResultRepo.saveAndFlush(result1);

        //save lesson result for user 35
        LessonHomeworkResult lessonHomeworkResult = new LessonHomeworkResult();
        lessonHomeworkResult.setScore(20d);
        lessonHomeworkResult.setType("Lesson");
        lessonHomeworkResult.setUserId(user.getId());
        lessonHomeworkResult.setHomework(lessonHomework);
        lessonHomeworkResultRepo.saveAndFlush(lessonHomeworkResult);

        return ResponseEntity.ok(homeworkResultRepo.findByUserId(34l));
    }

}


