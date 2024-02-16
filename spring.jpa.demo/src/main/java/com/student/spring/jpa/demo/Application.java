package com.student.spring.jpa.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.student.spring.jpa.demo.dao.StudentDAO;
import com.student.spring.jpa.demo.entity.Student;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * We will use this Bean for command line application.
	 * Executed after the Spring Beans have been loaded
	 */
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			System.out.println("Starting Demo...");
			
			createStudent(studentDAO);
			//createStudents(studentDAO);
			
			//findStudentById(studentDAO);
			//findAllStudents(studentDAO);
			//findStudentByLastName(studentDAO);
			
			//updateStudent(studentDAO);
			//updateStudentByJPQL(studentDAO);
			
			//deleteStudent(studentDAO);
			//deleteAllStudent(studentDAO);
			
			System.out.println("Demo Completed...");
		};
	}
	
	private void createStudent(StudentDAO studentDAO) {
		//Create student record
		Student student = new Student("Tom", "Hanks", "tommy@gmail.com");
		
		//Save the record
		studentDAO.save(student);
		
		//Fetch the student record
		System.out.println("ID generated for student >>> " + student.getId());
	}
	
	private void createStudents(StudentDAO studentDAO) {
		//Create student records
		Student student1 = new Student("John", "Doe", "doe@gmail.com");
		Student student2 = new Student("Mary", "Kom", "kom@gmail.com");
		Student student3 = new Student("Silver", "Swirl", "swirl@gmail.com");
		
		//Save the record
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
		
		System.out.println("Few more students created !");
	}
	
	private void findStudentById(StudentDAO studentDAO) {
		//Create student record
		Student student = new Student("John", "Fetch", "doe@gmail.com");
		
		//Save the record
		studentDAO.save(student);
		System.out.println("Generated ID for the student is >>> " + student.getId());
		
		//
		Student fetchedStudent = studentDAO.findById(student.getId());
		System.out.println("Student found using id >>> " + fetchedStudent);
	}
	
	private void findAllStudents(StudentDAO studentDAO) {
		//Query all the student from the DB
		List<Student> students = studentDAO.findAllStudent();
		
		//Print all the records
		for(Student student : students) {
			System.out.println(student);
		}
	}
	
	private void findStudentByLastName(StudentDAO studentDAO) {
		//Get the student
		List<Student> students = studentDAO.findByLastName("Fetch");
		
		//Print all the records
		for(Student student : students) {
			System.out.println(student);
		}
	}
	
	private void updateStudent(StudentDAO studentDAO) {
		//Fetch a student record using ID
		Student student = studentDAO.findById(6);
		System.out.println("Student fetched using ID " + student);
		
		//Change the firstName
		student.setFirstName("Subhajit");
		
		//Update the Student instance
		studentDAO.updateStudent(student);
		System.out.println("Student record after update " + student);
	}
	
	private void updateStudentByJPQL(StudentDAO studentDAO) {
		//Fetch a student record using ID
		String query = "update Student set lastName = 'Osborne'";
		
		//Update the Student instance
		int affectedRowCount = studentDAO.updateStudent(query);
		System.out.println("Number of record after update " + affectedRowCount);
	}
	
	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(6);
	}
	
	private void deleteAllStudent(StudentDAO studentDAO) {
		//Call the DAO method
		System.out.println("Going to delete all students...");
		int rowsDeleted = studentDAO.deleteAll();
		
		//Print number of rows deleted
		System.out.println("Number of rows deleted >>> " + rowsDeleted);
	}
}
