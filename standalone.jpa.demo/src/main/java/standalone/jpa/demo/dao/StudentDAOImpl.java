package standalone.jpa.demo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import standalone.jpa.demo.entity.Student;
import standalone.jpa.demo.utility.ConnectionManager;

public class StudentDAOImpl implements StudentDAO {

	private EntityManager entityManager;
	
	public StudentDAOImpl() {
		this.entityManager = new ConnectionManager().getEntityManager();
	}
	
	public void close() {
		if(entityManager != null)
			entityManager.close();
	}

	@Override
	public void save(Student student) {
		EntityTransaction transaction = entityManager.getTransaction();
		 
		try {
			transaction.begin();
			entityManager.persist(student);
			transaction.commit();
			
		} catch (Exception exception) {
            if(transaction.isActive()) 
            	transaction.rollback();
            exception.printStackTrace();
        }
	}

	@Override
	public Student findById(Integer id) {
		EntityTransaction transaction = entityManager.getTransaction();
		Student student = null; 
		try {
			transaction.begin();
			student = entityManager.find(Student.class, id);
			transaction.commit();
			
		} catch (Exception exception) {
            if(transaction.isActive()) 
            	transaction.rollback();
            exception.printStackTrace();
        }
		return student;
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
	//@Transactional
	public void updateStudent(Student student) {
		//Persist the updated student record
		entityManager.merge(student);
	}

	@Override
	//@Transactional
	public void delete(Integer id) {
		//Retrieve the student
		Student fetchedStudent = findById(id);
		System.out.println("Fetched student using ID >>> " + fetchedStudent);
		
		//Delete the student from DB
		entityManager.remove(fetchedStudent);
		System.out.println("Student removed...");
	}

	@Override
	//@Transactional
	public int deleteAll() {
		//Prepare delete query
		int rowsDeleted = entityManager.createQuery("delete from Student").executeUpdate();
		return rowsDeleted;
	}
}
