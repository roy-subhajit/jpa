package spring.jpa.mappings.demo.one.to.one.bi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import spring.jpa.mappings.demo.one.to.one.bi.entity.InstructorDetail;

//This annotation supports "Component Scanning" and Translates JDBC Exceptions
@Repository
public class InstructorDAOImpl implements InstructorDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public InstructorDetail findInstructorDetailById(Integer id) {
		InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
		return instructorDetail;
	}

	@Override
	@Transactional
	public void deleteInstuctorDetailById(Integer id) {
		//Retrieve InstructorDetail object
		InstructorDetail instructorDetail = findInstructorDetailById(id);
		
		//Finally delete. This will Cascade Delete Instructor object because of the configuration in Entity class
		entityManager.remove(instructorDetail);
	}

	@Override
	@Transactional
	public void deleteInstuctorDetailOnlyById(Integer id) {
		//Retrieve InstructorDetail object
		InstructorDetail instructorDetail = findInstructorDetailById(id);
		
		//Break the association (Bi-Directional Link) between Instructor and InstructorDetail
		instructorDetail.getInstructor().setInstructorDetail(null);
		
		//This will Cascade Delete Instructor object only
		//This is because CascadeType is currently not set as REMOVE but everything else
		entityManager.remove(instructorDetail);
	}
}
