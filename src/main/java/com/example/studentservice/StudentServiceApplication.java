package com.example.studentservice;

import com.example.studentservice.entity.EligibilityCriteria;
import com.example.studentservice.repository.EligibilityCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentServiceApplication implements CommandLineRunner {

	@Autowired
	private EligibilityCriteriaRepository criteriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Initialize default criteria
		EligibilityCriteria criteria = new EligibilityCriteria();
		criteria.setId(1L);
		criteria.setScienceThreshold(85);
		criteria.setMathsThreshold(90);
		criteria.setEnglishThreshold(75);
		criteria.setComputerThreshold(95);
		criteriaRepository.save(criteria);
	}
}
