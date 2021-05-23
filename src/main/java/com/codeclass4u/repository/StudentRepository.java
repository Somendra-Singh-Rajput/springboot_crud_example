package com.codeclass4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codeclass4u.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	//To find a student by ID
	Student findStudentById(Integer id);
	
}
