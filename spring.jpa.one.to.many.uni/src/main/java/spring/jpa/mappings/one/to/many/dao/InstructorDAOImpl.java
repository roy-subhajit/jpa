package spring.jpa.mappings.one.to.many.dao;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import spring.jpa.mappings.one.to.many.entity.Course;

//This annotation supports "Component Scanning" and Translates JDBC Exceptions
@Repository
public class InstructorDAOImpl implements InstructorDAO {

	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional
	public void addCourseWithReviews(Course course) {
		entityManager.persist(course);
	}

	@Override
	//@Transactional
	public Course fetchCourseWithReviewsByCourseId(Integer id) {
		
		//This is an alternative to get the many relationship when fetch type is lazy.
		//But this is discouraged because this is a hibernate specific solution and not JPA standard
		//Uncomment the @Transactional to use it as it needs a transaction to work
		
		//Course course = entityManager.find(Course.class, id);
		//Hibernate.initialize(course.getReviews());
		//return course;
		
		//The suggested solution is to use JOIN FETCH using criteria query
		TypedQuery<Course> createQuery = entityManager.createQuery("select cr from Course cr "
				+ "join fetch cr.reviews "
				+ "where cr.id = :idVal", Course.class);
		createQuery.setParameter("idVal", id);
		
		return createQuery.getSingleResult();
	}

	@Override
	@Transactional
	public void deleteCourseById(Integer id) {
		//Find
		Course course = entityManager.find(Course.class, id);
		
		//Delete
		//Associated Reviews will be deleted too, CascadeType.ALL
		entityManager.remove(course);
	}
}
