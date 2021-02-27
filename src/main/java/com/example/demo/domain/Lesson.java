package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Lesson extends AbstractGeneral {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;

    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    private Set<LessonHomework> lessonHomeworks;

    private String label;

}
