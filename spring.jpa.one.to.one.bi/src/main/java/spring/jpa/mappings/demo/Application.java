package spring.jpa.mappings.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.jpa.mappings.demo.one.to.one.bi.dao.InstructorDAO;
import spring.jpa.mappings.demo.one.to.one.bi.entity.InstructorDetail;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
		return runner -> {
			System.out.println("Starting Demo...");
			
			findInstructorDetailById(instructorDAO);
			//deleteInstructorDetailById(instructorDAO);
			//deleteInstructorDetailOnlyById(instructorDAO);
			
			System.out.println("Demo Completed...");
		};
	}
	
	private void findInstructorDetailById(InstructorDAO instructorDAO) {
		//Call the DAO layer
		InstructorDetail instructorDetail = instructorDAO.findInstructorDetailById(1);
		
		System.out.println("Received InstructorDetail instance >>> " + instructorDetail);
		System.out.println("Received Instructor instance >>> " + instructorDetail.getInstructor());
	}
	
	private void deleteInstructorDetailById(InstructorDAO instructorDAO) {	
		System.out.println("Going to Cascade Delete InstructorDetail record");
		instructorDAO.deleteInstuctorDetailById(21);
		System.out.println("Record deleted");
	}
	
	/**
	 * We need to explicitly define the FetchTypes i.e. without DELETE in the Entity class
	 * @OneToOne mapping annotation
	 * 
	 * @param instructorDAO
	 */
	private void deleteInstructorDetailOnlyById(InstructorDAO instructorDAO) {	
		System.out.println("Going to delete only InstructorDetail record");
		instructorDAO.deleteInstuctorDetailOnlyById(41);
		System.out.println("Record deleted");
	}
}
