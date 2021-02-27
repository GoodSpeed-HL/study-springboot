package com.example.demo.domain;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractGeneral implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifiedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

}
