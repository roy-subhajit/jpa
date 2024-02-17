package spring.jpa.mappings.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.jpa.mappings.demo.one.to.one.uni.dao.InstructorDAO;
import spring.jpa.mappings.demo.one.to.one.uni.entity.Instructor;
import spring.jpa.mappings.demo.one.to.one.uni.entity.InstructorDetail;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
		return runner -> {
			System.out.println("Starting Demo...");
			
			saveInstructor(instructorDAO);
			//findInstructorById(instructorDAO);
			//deleteInstructorById(instructorDAO);
			
			System.out.println("Demo Completed...");
		};
	}
	
	private void saveInstructor(InstructorDAO instructorDAO) {
		//Create the record
		InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com/myChannel", "gardening");
		Instructor instructor = new Instructor("Subhajit", "Roy", "myemail@gmail.com");
		
		//Set the mapping with reference table (InstructorDetail)
		instructor.setInstructorDetail(instructorDetail);
		
		//Finally save the record in DB
		instructorDAO.save(instructor);
		System.out.println("Instructor record created with id >>> " + instructor.getId());
	}
	
	private void findInstructorById(InstructorDAO instructorDAO) {
		//Find the instructor
		Instructor instructor = instructorDAO.findInstructorById(1);
		
		//Print the fetched instructor
		System.out.println(instructor);
	}
	
	private void deleteInstructorById(InstructorDAO instructorDAO) {
		//Find the instructor and delete the record. Observe that Instructor Detail record has been deleted too.
		instructorDAO.deleteInstructorById(1);
		System.out.println("Instructor deleted...");
	}
}
