package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name", length = 15, nullable = false)
    private String name;

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "Mobile", length = 10, nullable = false, unique = true)
    private String mobile;

    @Column(name = "Email", length = 25, nullable = false, unique = true)
    private String email;
    
    @Column(name="Password", length=10)
    private String password;

    @Column(name = "District", length = 30)
    private String dist;

    @Column(name = "City", length = 30)
    private String city;

    @Column(name = "Address", length = 50)
    private String address;
    
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

}
