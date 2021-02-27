package com.example.demo.domain;


import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Assignment")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class AssignmentResult extends HomeworkResult {

}
