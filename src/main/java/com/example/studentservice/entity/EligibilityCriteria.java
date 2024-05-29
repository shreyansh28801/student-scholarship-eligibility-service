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
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "eligibility_criteria")
public class EligibilityCriteria {
    @Id
    private Long id;
    private Integer scienceThreshold;
    private Integer mathsThreshold;
    private Integer englishThreshold;
    private Integer computerThreshold;
}
