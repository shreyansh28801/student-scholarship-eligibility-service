package com.example.studentservice.controller;

import com.example.studentservice.entity.EligibilityCriteria;
import com.example.studentservice.entity.Student;
import com.example.studentservice.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
@Tag(name = "Student API", description = "API for student scholarship eligibility")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/upload")
    @Operation(summary = "Upload CSV file and start processing")
    public CompletableFuture<ResponseEntity<String>> uploadFile(@RequestParam("file") MultipartFile file) {
        return studentService.processFile(file).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/student/{rollNumber}")
    @Operation(summary = "Get student eligibility status by roll number")
    public ResponseEntity<Student> getStudentStatus(@PathVariable Long rollNumber) {
        Student student = studentService.getStudentStatus(rollNumber);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/criteria")
    @Operation(summary = "Update scholarship eligibility criteria")
    public ResponseEntity<Void> updateCriteria(@RequestBody EligibilityCriteria criteria) {
        studentService.updateCriteria(criteria);
        return ResponseEntity.ok().build();
    }
}

