package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends AbstractGeneral{

    private String label;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Homework> homeworks;
}
