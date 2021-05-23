package com.codeclass4u.service;

import java.util.List;
import java.util.Optional;

import com.codeclass4u.model.Student;

public interface StudentService {
	
	//To fetch all students as list
	List<Student> listStudents();
	
	//To add a student
	Student addStudent(Student student);
	
	//To fetch one student
	Optional<Student> getStudent(int id);
	
	//To update a student
	Student updateStudent(Student student, int id);
	
	//To delete a student
	void deleteStudent(int id);
	
}
