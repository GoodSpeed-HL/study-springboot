package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Project")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Project extends Homework{

}
