package com.example.studentservice.service;

import com.example.studentservice.entity.EligibilityCriteria;
import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.EligibilityCriteriaRepository;
import com.example.studentservice.repository.StudentRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EligibilityCriteriaRepository criteriaRepository;

    @Async
    public CompletableFuture<String> processFile(MultipartFile file) {
        String jobId = UUID.randomUUID().toString();
        CompletableFuture.runAsync(() -> {
            try (Reader reader = new InputStreamReader(file.getInputStream())) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
                List<CSVRecord> records = csvParser.getRecords();
                EligibilityCriteria criteria = criteriaRepository.findById(1L).orElse(null);

                for (CSVRecord record : records) {
                    Student student = new Student();
                    student.setRollNumber(Long.parseLong(record.get("roll number")));
                    student.setName(record.get("student name"));
                    student.setScience(Integer.parseInt(record.get("science")));
                    student.setMaths(Integer.parseInt(record.get("maths")));
                    student.setEnglish(Integer.parseInt(record.get("english")));
                    student.setComputer(Integer.parseInt(record.get("computer")));

                    boolean isEligible = checkEligibility(student, criteria);
                    student.setEligible(isEligible ? "YES" : "NO");
                    studentRepository.save(student);
                }
            } catch (IOException e) {
                // Log the exception
            }
        });
        return CompletableFuture.completedFuture(jobId);
    }

    @Cacheable(value = "students", key = "#rollNumber")
    public Student getStudentStatus(Long rollNumber) {
        return studentRepository.findById(rollNumber).orElse(null);
    }

    public void updateCriteria(EligibilityCriteria criteria) {
        criteriaRepository.save(criteria);
    }

    public boolean checkEligibility(Student student, EligibilityCriteria criteria) {
        return student.getScience() > criteria.getScienceThreshold() &&
                student.getMaths() > criteria.getMathsThreshold() &&
                student.getEnglish() > criteria.getEnglishThreshold() &&
                student.getComputer() > criteria.getComputerThreshold();
    }
}

