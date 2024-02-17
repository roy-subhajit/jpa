package spring.jpa.mappings.one.to.many.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Student;

//This annotation supports "Component Scanning" and Translates JDBC Exceptions
@Repository
public class InstructorDAOImpl implements InstructorDAO {

	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional
	public void saveCourse(Course course) {
		entityManager.persist(course);
	}

	@Override
	public Course findCourseAndStudentByCourseId(Integer id) {
		TypedQuery<Course> createQuery = entityManager.createQuery("select cr from Course cr "
				+ "JOIN FETCH cr.students "
				+ "where cr.id = :idVal", Course.class);
		createQuery.setParameter("idVal", id);
		
		return createQuery.getSingleResult();
	}

	@Override
	public Student findStudentAndCourseByStudentId(Integer id) {
		TypedQuery<Student> createQuery = entityManager.createQuery("select st from Student st "
				+ "JOIN FETCH st.courses "
				+ "where st.id = :idVal", Student.class);
		createQuery.setParameter("idVal", id);
		
		return createQuery.getSingleResult();
	}

	@Override
	@jakarta.transaction.Transactional
	public void saveStudent(Student student) {
		entityManager.merge(student);
	}

	@Override
	@jakarta.transaction.Transactional
	public void deleteCourseById(Integer id) {
		// TODO Auto-generated method stub
		Course course = findCourseAndStudentByCourseId(id);
		entityManager.remove(course);
	}

	@Override
	@jakarta.transaction.Transactional
	public void deleteStudentById(Integer id) {
		// TODO Auto-generated method stub
		Student student = findStudentAndCourseByStudentId(id);
		entityManager.remove(student);
	}
}
