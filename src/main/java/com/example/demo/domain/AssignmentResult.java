package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Assignment")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class AssignmentResult extends HomeworkResult {

}
