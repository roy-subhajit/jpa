package spring.jpa.mappings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.jpa.mappings.one.to.many.dao.InstructorDAO;
import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Instructor;
import spring.jpa.mappings.one.to.many.entity.InstructorDetail;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
		return runner -> {
			System.out.println("Starting Demo...");
			
			createInstructorWithCourse(instructorDAO);
			//updateInstructor(instructorDAO);
			//updateCourse(instructorDAO);
			//deleteInstructorById(instructorDAO);
			//deleteCourseById(instructorDAO);
			
			System.out.println("Demo Completed...");
		};
	}
	
	/**
	 * Creation of Instructor object along with InstructorDetail (OneToOne) and Course (OneToMany)
	 * @param instructorDAO
	 */
	public void createInstructorWithCourse(InstructorDAO instructorDAO) {
		//Create the record
		InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com/myChannel", "gardening");
		Instructor instructor = new Instructor("Subhajit", "Roy", "myemail@gmail.com");
		Course english = new Course("English");
		Course history = new Course("History");
		
		//Set the mapping data of Instructor Detail and Course with Instructor
		instructor.setInstructorDetail(instructorDetail);
		instructor.addCourse(english);
		instructor.addCourse(history);
		
		//Save the record to Oracle
		instructorDAO.save(instructor);
		System.out.println("Done...");
	}
	
	/**
	 * Straight forward and simple update
	 * @param instructorDAO
	 */
	public void updateInstructor(InstructorDAO instructorDAO) {
		//Fetch Instructor which we will update
		Instructor instructor = instructorDAO.findInstructorById(1);
		
		//Update the lastName of the Instructor
		instructor.setEmail("newemail@gmail.com");
		
		//Finally update Instructor
		instructorDAO.update(instructor);
	}
	
	/**
	 * Straight forward and simple update of Course
	 * @param instructorDAO
	 */
	public void updateCourse(InstructorDAO instructorDAO) {
		//Find a course by ID to update
		Course course = instructorDAO.findCourseById(2);
		
		//Change a field, here I'm changing title
		course.setTitle("World History");
		
		//Update the course
		instructorDAO.updateCourse(course);
		
		System.out.println("Done !!");
	}
	
	/**
	 * Check the DAO implementation comments
	 * @param instructorDAO
	 */
	public void deleteInstructorById(InstructorDAO instructorDAO) {
		instructorDAO.deleteInstructorById(22);
		System.out.println("Instructor deleted...");
	}
	
	public void deleteCourseById(InstructorDAO instructorDAO) {
		instructorDAO.deleteCourseById(23);
		System.out.println("Courses deleted...");
	}
}
