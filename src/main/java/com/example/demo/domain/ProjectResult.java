package com.example.demo.domain;


import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Project")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProjectResult extends HomeworkResult {

}
