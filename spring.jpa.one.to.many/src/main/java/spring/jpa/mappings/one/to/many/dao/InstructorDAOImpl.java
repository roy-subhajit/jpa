package spring.jpa.mappings.one.to.many.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Instructor;

//This annotation supports "Component Scanning" and Translates JDBC Exceptions
@Repository
public class InstructorDAOImpl implements InstructorDAO {

	@Autowired
	EntityManager entityManager;
	
	/**
	 * This method will save Instructor along with InstructorDetail object and Course object.
	 * It is because we have set CascadeType.ALL and CascadeType.PERSIST respectively 
	 * in the Instructor Entity class
	 */
	@Override
	@Transactional
	public void save(Instructor instructor) {
		entityManager.persist(instructor);
	}

	@Override
	@Transactional
	public void update(Instructor instructor) {
		entityManager.merge(instructor);
	}

	/**
	 * This will also fetch the InstructorDetail object.
	 * Because of default behavior of @OneToOne fetch type which is 'EAGER'
	 */
	@Override
	public Instructor findInstructorById(Integer id) {
		Instructor fetchedInstructorObject = entityManager.find(Instructor.class, id);
		return fetchedInstructorObject;
	}

	@Override
	public Course findCourseById(Integer id) {
		Course course = entityManager.find(Course.class, id);
		return course;
	}

	@Override
	@Transactional
	public void updateCourse(Course course) {
		entityManager.merge(course);
	}

	@Override
	@Transactional
	public void deleteInstructorById(Integer id) {
		//Retrieve the Instructor
		Instructor instructor = entityManager.find(Instructor.class, id);
		
		//NOTE 1
		//Try commenting out below and check Foreign Key violation issue (child record found).
		//Remember that in DB Cascade type is not set and in Instructor entity we have removed CascadeType.REMOVE
		//Hence the issue and thus we need to manually dissociate the relation between Instructor and it's courses
		//NOTE 2
		//Notice that here we are able to fetch the courses even when the FetchType is defaulted (LAZY) for OneToMany.
		//This is possible because here all Hibernate calls are under a single session due to the Transaction
		//annotation set in this method.
		List<Course> courses = instructor.getCourses();		for(Course course : courses)
			course.setInstructor(null);
		
		//finally delete it
		entityManager.remove(instructor);
	}

	@Override
	@jakarta.transaction.Transactional
	public void deleteCourseById(Integer id) {
		//Fetch the course to be deleted
		Course course = findCourseById(id);
		
		//Delete it
		entityManager.remove(course);
	}
}
