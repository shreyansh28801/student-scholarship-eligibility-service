package com.example.studentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    private Long rollNumber;
    private String name;
    private Integer science;
    private Integer maths;
    private Integer english;
    private Integer computer;
    private String eligible;

}
