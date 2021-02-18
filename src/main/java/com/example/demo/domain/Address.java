package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AbstractGeneral{

    private String addr1;

    private String city;

    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
