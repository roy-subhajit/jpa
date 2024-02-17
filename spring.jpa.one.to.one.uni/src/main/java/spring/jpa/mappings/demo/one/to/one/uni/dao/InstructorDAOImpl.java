package spring.jpa.mappings.demo.one.to.one.uni.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import spring.jpa.mappings.demo.one.to.one.uni.entity.Instructor;

//This annotation supports "Component Scanning" and Translates JDBC Exceptions
@Repository
public class InstructorDAOImpl implements InstructorDAO {

	@Autowired
	EntityManager entityManager;
	
	/**
	 * This method will save Instructor along with InstructorDetail object
	 * It is because we have set CascadeType.ALL in the Entity class
	 */
	@Override
	@Transactional
	public void save(Instructor instructor) {
		entityManager.persist(instructor);
	}

	/**
	 * This will also fetch the InstructorDetail object.
	 * Because of default behavior of @OneToOne fetch type is 'eager'
	 * We will discuss about fetch types later
	 */
	@Override
	public Instructor findInstructorById(Integer id) {
		//The find method uses the PK (second argument) of the Entity class(first argument)
		Instructor fetchedInstructorObject = entityManager.find(Instructor.class, id);
		return fetchedInstructorObject;
	}

	@Override
	@Transactional
	public void deleteInstructorById(Integer id) {
		//Fetch the instructor record using id
		Instructor fetchedInstructorObject = findInstructorById(id);
		
		//Delete the record
		entityManager.remove(fetchedInstructorObject);
	}
}
