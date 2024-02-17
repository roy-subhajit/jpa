package spring.jpa.mappings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.jpa.mappings.one.to.many.dao.InstructorDAO;
import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Student;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
		return runner -> {
			System.out.println("Starting Demo...");
			
			saveCourseWithStudent(instructorDAO);
			//findCourseAndStudentByCourseId(instructorDAO);
			//findStudentAndCourseByStudentId(instructorDAO);
			//addMoreCoursesForAStudent(instructorDAO);
			//deleteCourseById(instructorDAO);
			//deleteStudentById(instructorDAO);
			
			System.out.println("Demo Completed...");
		};
	}
	
	private void deleteStudentById(InstructorDAO instructorDAO) {
		instructorDAO.deleteStudentById(1);
	}
	
	private void deleteCourseById(InstructorDAO instructorDAO) {
		instructorDAO.deleteCourseById(1);
	}
	
	private void addMoreCoursesForAStudent(InstructorDAO instructorDAO) {
		Student student = instructorDAO.findStudentAndCourseByStudentId(1);
		System.out.println("Going to add couple of courses for student >>> " + student.getFirstName());
		
		Course courseOne = new Course("JSP-Servlet");
		Course courseTwo = new Course("DBMS");
		
		student.addCourse(courseOne);
		student.addCourse(courseTwo);
		
		instructorDAO.saveStudent(student);
		System.out.println("Student is saved");
	}
	
	private void findStudentAndCourseByStudentId(InstructorDAO instructorDAO) {
		Student student = instructorDAO.findStudentAndCourseByStudentId(1);
		System.out.println(student);
		System.out.println(student.getCourses());
	}
	
	private void findCourseAndStudentByCourseId(InstructorDAO instructorDAO) {
		Course course = instructorDAO.findCourseAndStudentByCourseId(1);
		System.out.println(course.getStudents());
	}
	
	private void saveCourseWithStudent(InstructorDAO instructorDAO) {
		Course course = new Course("Learning JPA");
		
		Student studentOne = new Student("Tom", "Hanks", "t@gmail.com");
		Student studentTwo = new Student("Harry", "Gomes", "h@gmail.com");
		
		course.addStudent(studentOne);
		course.addStudent(studentTwo);
		
		instructorDAO.saveCourse(course);
	}
}
