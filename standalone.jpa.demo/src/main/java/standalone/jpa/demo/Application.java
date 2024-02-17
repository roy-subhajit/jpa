package standalone.jpa.demo;

import java.util.List;

import standalone.jpa.demo.dao.StudentDAO;
import standalone.jpa.demo.dao.StudentDAOImpl;
import standalone.jpa.demo.entity.Student;

public class Application {

	public static void main(String[] args) {
		//No Spring Beans here, so create one yourself :)
		StudentDAO studentDAO = new StudentDAOImpl();
		
		//createStudent(studentDAO);
		createStudents(studentDAO);
		
		//findStudentById(studentDAO);
		//findAllStudents(studentDAO);
		//findStudentByLastName(studentDAO);
		
		//updateStudent(studentDAO);
		
		//deleteStudent(studentDAO);
		//deleteAllStudent(studentDAO);
	}
	
	private static void createStudent(StudentDAO studentDAO) {
		//Create student record
		Student student = new Student("Tom", "Hanks", "tommy@gmail.com");
		
		//Save the record
		studentDAO.save(student);
		
		//Fetch the student record
		System.out.println("ID generated for student >>> " + student.getId());
		
		//Close EntityManager
		studentDAO.close();
	}
	
	private static void createStudents(StudentDAO studentDAO) {
		//Create student records
		Student student1 = new Student("John", "Doe", "doe@gmail.com");
		Student student2 = new Student("Mary", "Kom", "kom@gmail.com");
		Student student3 = new Student("Silver", "Swirl", "swirl@gmail.com");
		
		//Save the record
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
		
		System.out.println("Few more students created !");
		
		//Close EntityManager
		studentDAO.close();
	}
	
	private static void findStudentById(StudentDAO studentDAO) {
		//Create student record
		Student student = new Student("John", "Fetch", "doe@gmail.com");
		
		//Save the record
		studentDAO.save(student);
		System.out.println("Generated ID for the student is >>> " + student.getId());
		
		//Find the student
		Student fetchedStudent = studentDAO.findById(student.getId());
		System.out.println("Student found using id >>> " + fetchedStudent);
		
		//Close EntityManager
		studentDAO.close();
	}
	
	private static void findAllStudents(StudentDAO studentDAO) {
		//Query all the student from the DB
		List<Student> students = studentDAO.findAllStudent();
		
		//Print all the records
		for(Student student : students) {
			System.out.println(student);
		}
	}
	
	private static void findStudentByLastName(StudentDAO studentDAO) {
		//Get the student
		List<Student> students = studentDAO.findByLastName("Fetch");
		
		//Print all the records
		for(Student student : students) {
			System.out.println(student);
		}
	}
	
	private static void updateStudent(StudentDAO studentDAO) {
		//Fetch a student record using ID
		Student student = studentDAO.findById(6);
		System.out.println("Student fetched using ID " + student);
		
		//Change the firstName
		student.setFirstName("Subhajit");
		
		//Update the Student instance
		studentDAO.updateStudent(student);
		System.out.println("Student record after update " + student);
	}
	
	private static void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(6);
	}
	
	private static void deleteAllStudent(StudentDAO studentDAO) {
		//Call the DAO method
		System.out.println("Going to delete all students...");
		int rowsDeleted = studentDAO.deleteAll();
		
		//Print number of rows deleted
		System.out.println("Number of rows deleted >>> " + rowsDeleted);
	}
}
