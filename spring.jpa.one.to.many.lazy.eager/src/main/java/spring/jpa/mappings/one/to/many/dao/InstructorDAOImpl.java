package spring.jpa.mappings.one.to.many.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Instructor;

//This annotation supports "Component Scanning" and Translates JDBC Exceptions
@Repository
public class InstructorDAOImpl implements InstructorDAO {

	@Autowired
	EntityManager entityManager;


	/**
	 * This will fetch the InstructorDetail object because of @OneToOne relationship default fetch type.
	 * However if fetching of Course object(s) is tried will raise Exception because of @OneToMany default fetch type.
	 * 
	 * Default behavior of @OneToOne fetch type is 'EAGER'
	 * Default behavior of @OneToMany fetch type is 'LAZY'
	 */
	@Override
	public Instructor findInstructorById(Integer id) {
		Instructor fetchedInstructorObject = entityManager.find(Instructor.class, id);
		return fetchedInstructorObject;
	}

	/**
	 * Again make sure that the name of the table and column should come from the Entity class.
	 * Do note the usage of "instructor.id"
	 * Also the names are case sensitive.
	 */
	@Override
	public List<Course> findCoursesByInstructorId(Integer id) {
		TypedQuery<Course> createQuery = entityManager.
				createQuery("from Course where instructor.id = :idVal", Course.class);
		createQuery.setParameter("idVal", id);
		
		return createQuery.getResultList();
	}

	/**
	 * Note here, even when @OneToMany fetch type is 'LAZY' it would fetch all Courses here.
	 * The JOIN FETCH is similar to EAGER loading
	 */
	@Override
	public Instructor findInstructorByIdUsingJoinFetch(Integer id) {
		TypedQuery<Instructor> createQuery = entityManager.createQuery(
				"select itr from Instructor itr "
				+ "JOIN FETCH itr.courses "
				+ "where itr.id = :idVal", Instructor.class);
		createQuery.setParameter("idVal", id);
		
		return createQuery.getSingleResult();
	}

	/**
	 * Like the last method this will also fetch Instructor Detail object at one shot.
	 * Compare the SQL between the last method and this method. Notice that SQL related to fetching only Instructor Detail
	 * is not coming anymore.
	 */
	@Override
	public Instructor findInstructorByIdUsingMultipleJoinFetch(Integer id) {
		TypedQuery<Instructor> createQuery = entityManager.createQuery(
				"select itr from Instructor itr "
				+ "JOIN FETCH itr.courses "
				+ "JOIN FETCH itr.instructorDetail "
				+ "where itr.id = :idVal", Instructor.class);
		createQuery.setParameter("idVal", id);
		
		return createQuery.getSingleResult();
	}
}
