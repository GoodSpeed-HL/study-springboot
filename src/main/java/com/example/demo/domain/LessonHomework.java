package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "LessonHomework")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
public class LessonHomework extends Homework {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;
}
