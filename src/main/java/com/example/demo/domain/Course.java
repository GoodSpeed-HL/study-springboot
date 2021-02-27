package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Course extends AbstractGeneral{

    private String label;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Homework> homeworks;
}
