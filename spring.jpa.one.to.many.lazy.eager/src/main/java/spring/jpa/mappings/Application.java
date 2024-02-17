package spring.jpa.mappings;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.jpa.mappings.one.to.many.dao.InstructorDAO;
import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Instructor;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
		return runner -> {
			System.out.println("Starting Demo...");
			
			fetchInstructorById(instructorDAO);
			//fetchCoursesLazilyForAnInstructor(instructorDAO);
			//findInstructorByIdUsingJoinFetch(instructorDAO);
			//findInstructorByIdUsingMultipleJoinFetch(instructorDAO);
			
			System.out.println("Demo Completed...");
		};
	}

	/**
	 * Note here what happens when we don't explicitly set FetchType to EAGER 
	 * for entity Instructor @OneToMany relation. 
	 * 
	 * Because @OneToMany default is LAZY, it will fail with LazyInitializationException
	 */
	private void fetchInstructorById(InstructorDAO instructorDAO) {
		// TODO Auto-generated method stub
		Instructor instructor = instructorDAO.findInstructorById(22);
		
		//Fetch the Details
		System.out.println(instructor.getInstructorDetail());
		
		//Fetch Courses. Do note the error will come only when we call the getCourses method below.
		//Try commenting the below, the method should run fine printing Instructor and InstructorDetail
		for(Course course : instructor.getCourses())
			System.out.println(course);
	}
	
	/**
	 * 
	 * @param instructorDAO
	 */
	private void fetchCoursesLazilyForAnInstructor(InstructorDAO instructorDAO) {
		// TODO Auto-generated method stub
		Instructor instructor = instructorDAO.findInstructorById(1);
		
		//Fetch the Details
		System.out.println(instructor.getInstructorDetail());
		
		//Fetch courses Lazily
		List<Course> courses = instructorDAO.findCoursesByInstructorId(1);
		
		//Print fetched Courses
		for(Course course : courses)
			System.out.println(course);
		
	}
	
	/**
	 * An example of JOIN FETCH applied for course
	 * @param instructorDAO
	 */
	private void findInstructorByIdUsingJoinFetch(InstructorDAO instructorDAO) {
		//Fetch using ID. This will fetch courses as well because of JOIN FETCH, acting similar to EAGER fetch type.
		Instructor instructor = instructorDAO.findInstructorByIdUsingJoinFetch(1);
		
		//Print the detail object
		System.out.println(instructor.getInstructorDetail());
		
		//Print all the course assigned to the Instructor
		for(Course course : instructor.getCourses())
			System.out.println(course);
	}
	
	private void findInstructorByIdUsingMultipleJoinFetch(InstructorDAO instructorDAO) {
		//Fetch using ID. This will fetch courses as well because of JOIN FETCH, acting similar to EAGER fetch type.
		Instructor instructor = instructorDAO.findInstructorByIdUsingMultipleJoinFetch(1);
		
		//Print the detail object
		System.out.println(instructor.getInstructorDetail());
		
		//Print all the course assigned to the Instructor
		for(Course course : instructor.getCourses())
			System.out.println(course);
	}
}
