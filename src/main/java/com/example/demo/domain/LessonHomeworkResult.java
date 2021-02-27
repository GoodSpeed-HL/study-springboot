package com.example.demo.domain;


import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Lesson")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class LessonHomeworkResult extends HomeworkResult {

}
