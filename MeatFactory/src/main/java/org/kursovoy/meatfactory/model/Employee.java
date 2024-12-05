package org.kursovoy.meatfactory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "employees")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private int salary;
    private String role;
    private Boolean male;
    private String contactInfo;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private List<Order> orders;



}
