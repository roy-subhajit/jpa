package com.student.spring.jpa.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.student.spring.jpa.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

//This annotation supports "Component Scanning" and Translates JDBC Exceptions
@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private EntityManager entityManager;
	
	/**
	 * @Transactional annotation is required for DML operations, else will throw error
	 */
	@Override
	@Transactional
	public void save(Student student) {
		entityManager.persist(student);
	}

	@Override
	public Student findById(Integer id) {
		return entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> findAllStudent() {
		//Remember JPQL uses Java class properties name in queries and not actual database field names
		TypedQuery<Student> createQuery = entityManager.createQuery("FROM Student", Student.class);
		
		//Same as above but with sorting applied on field firstName
		//TypedQuery<Student> createQuery = entityManager.createQuery("FROM Student ORDER BY firstName ASC", Student.class);
		
		return createQuery.getResultList();
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		//Create query. Note in JPQL the query variables are prepended with a colon
		TypedQuery<Student> createQuery = entityManager.createQuery(
				"FROM Student where lastName = :variable", Student.class);
		
		//Set parameter
		createQuery.setParameter("variable", lastName);
		
		return createQuery.getResultList();
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		//Persist the updated student record
		entityManager.merge(student);
	}
	
	@Override
	@Transactional
	public int updateStudent(String query) {
		//Update using JPQL
		int rowsUpdated = entityManager.createQuery(query).executeUpdate();
		return rowsUpdated;
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		//Retrieve the student
		Student fetchedStudent = findById(id);
		System.out.println("Fetched student using ID >>> " + fetchedStudent);
		
		//Delete the student from DB
		entityManager.remove(fetchedStudent);
		System.out.println("Student removed...");
	}

	@Override
	@Transactional
	public int deleteAll() {
		//Prepare delete query
		int rowsDeleted = entityManager.createQuery("delete from Student").executeUpdate();
		return rowsDeleted;
	}
}
