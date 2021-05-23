package com.codeclass4u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeclass4u.exception.AddStudentException;
import com.codeclass4u.exception.IdNotExistException;
import com.codeclass4u.exception.NoStudentFoundException;
import com.codeclass4u.model.Student;
import com.codeclass4u.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping
	public ResponseEntity<?> getAllStudents(){
		
		try {
			
			return new ResponseEntity<>(studentService.listStudents(),HttpStatus.OK);	
			
		}catch(NoStudentFoundException e) {
			
			return new ResponseEntity<>("No student found, seems student table is empty.",HttpStatus.NOT_FOUND);
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}
	
	@PostMapping
	public ResponseEntity<?> addStudent(@RequestBody Student student){
		
		try {
			
			return new ResponseEntity<>(studentService.addStudent(student),HttpStatus.CREATED);
			
		}catch(AddStudentException e) {
			
			return new ResponseEntity<>("Student added,But might be null values passed or other problem occurred.",HttpStatus.NOT_IMPLEMENTED);
			
		}catch(Exception e) {
			
			return new ResponseEntity<>("Student added successfully.",HttpStatus.CREATED);
			
		}
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getStudent(@PathVariable Integer id){
		
		try {
			
			return new ResponseEntity<>(studentService.getStudent(id),HttpStatus.OK);
			
		}catch(IdNotExistException ine) {
			
			return new ResponseEntity<>("Id not found,Please check enetered Id whether it is correct or not.", HttpStatus.NOT_FOUND); 
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR); 
			
		}	
	
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Integer id){
		
		try {
			
			return new ResponseEntity<>(studentService.updateStudent(student,id),HttpStatus.OK);
			
		}catch(AddStudentException e) {
		
			return new ResponseEntity<>("Student details not updated, Passed values might be incorrect or might be other problme.",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Integer id){
		
		try {
			
			studentService.deleteStudent(id);
			
		}catch(IdNotExistException e) {
			
			return "Id not found.";
		}
		
		
		return "Record successfully deleted.";
	}
	
}
