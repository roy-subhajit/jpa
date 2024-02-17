package spring.jpa.mappings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.jpa.mappings.one.to.many.dao.InstructorDAO;
import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Review;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
		return runner -> {
			System.out.println("Starting Demo...");
			
			//addCourseWithReviews(instructorDAO);
			//fetchCourseWithReviewsByCourseId(instructorDAO);
			deleteCourseById(instructorDAO);
			
			System.out.println("Demo Completed...");
		};
	}
	
	private void addCourseWithReviews(InstructorDAO instructorDAO) {
		Course newCourse = new Course("JPA Course With Reviews");
		newCourse.addReview(new Review("Good course"));
		newCourse.addReview(new Review("Not so good course"));
		
		instructorDAO.addCourseWithReviews(newCourse);
	}
	
	private void fetchCourseWithReviewsByCourseId(InstructorDAO instructorDAO) {
		Course course = instructorDAO.fetchCourseWithReviewsByCourseId(1);
		System.out.println(course.getReviews());
	}
	
	private void deleteCourseById(InstructorDAO instructorDAO) {
		instructorDAO.deleteCourseById(1);
	}
}
