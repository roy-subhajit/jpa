package com.student.spring.jpa.demo.dao;

import java.util.List;

import com.student.spring.jpa.demo.entity.Student;

public interface StudentDAO {
	public void save(Student student);
	
	public Student findById(Integer id);
	public List<Student> findAllStudent();
	public List<Student> findByLastName(String lastName);
	
	public void updateStudent(Student student);
	public int updateStudent(String query);
	
	public void delete(Integer id);
	public int deleteAll();
}
