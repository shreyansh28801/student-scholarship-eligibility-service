package com.example.studentservice.service;

import com.example.studentservice.entity.EligibilityCriteria;
import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.EligibilityCriteriaRepository;
import com.example.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private EligibilityCriteriaRepository criteriaRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCheckEligibility() {
        Student student = new Student();
        student.setScience(90);
        student.setMaths(91);
        student.setEnglish(76);
        student.setComputer(96);

        EligibilityCriteria criteria = new EligibilityCriteria();
        criteria.setScienceThreshold(85);
        criteria.setMathsThreshold(90);
        criteria.setEnglishThreshold(75);
        criteria.setComputerThreshold(95);

        when(criteriaRepository.findById(1L)).thenReturn(Optional.of(criteria));

        boolean isEligible = studentService.checkEligibility(student, criteria);
        assertEquals(true, isEligible);
    }

    @Test
    public void testGetStudentStatus() {
        Student student = new Student();
        student.setRollNumber(100101L);
        student.setEligible("YES");

        when(studentRepository.findById(100101L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentStatus(100101L);
        assertEquals("YES", result.getEligible());
    }
}
