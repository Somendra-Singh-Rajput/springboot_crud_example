package com.codeclass4u.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeclass4u.exception.AddStudentException;
import com.codeclass4u.exception.IdNotExistException;
import com.codeclass4u.exception.NoStudentFoundException;
import com.codeclass4u.model.Student;
import com.codeclass4u.repository.StudentRepository;
import com.codeclass4u.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepository;
	
	//To gain the control used @Autowired on bean's setter method.
	@Autowired
	public void setStudentRepository(StudentRepository studentRepository) {	
		this.studentRepository = studentRepository;		
	}

	@Override
	public List<Student> listStudents() {		
		if(studentRepository.findAll() == null) {
			
			throw new NoStudentFoundException("No student found, Student table is empty.");
			
		}else {
			
			return studentRepository.findAll();
		}	
	}

	@Override
	public Student addStudent(Student student) {
		
		student = studentRepository.save(student);
		
		if(student.getName() == null || student.getEmail() == null || student.getStandard() == null){
			
			throw new AddStudentException("Student added,But might be null values passed or other problem occurred.");
			
		}else {
			return studentRepository.save(student);
		}
	}

	@Override
	public Optional<Student> getStudent(int id) {  //Used the optional to avoid the null pointer exception
		
			if(studentRepository.existsById(id)) {
				
				return studentRepository.findById(id);
			}else {
				
				throw new IdNotExistException("Id not found,Please check enetered Id whether it is correct or not.");
			}
			
	}

	@Override
	public Student updateStudent(Student student,int id) {
		if(studentRepository.existsById(id)) {	
			Student st = studentRepository.findStudentById(id);
			st.setName(student.getName());
			st.setEmail(student.getEmail());
			st.setStandard(student.getStandard());
			
			if(studentRepository.save(st) == null) {
				
				throw new AddStudentException("Student details not updated, Passed values might be incorrect or might be other problme.");
			}
			else {
				studentRepository.save(st);
				
				return st;
			}
			
		}else {
			return student;
		}
	}

	@Override
	public void deleteStudent(int id) {
		if(studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
		}else {
			throw new IdNotExistException("Id not found.");
		}		
	}

}
