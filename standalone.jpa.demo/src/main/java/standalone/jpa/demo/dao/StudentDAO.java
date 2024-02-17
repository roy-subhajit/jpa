package standalone.jpa.demo.dao;

import java.util.List;

import standalone.jpa.demo.entity.Student;

public interface StudentDAO {
	public void save(Student student);
	
	public Student findById(Integer id);
	public List<Student> findAllStudent();
	public List<Student> findByLastName(String lastName);
	
	public void updateStudent(Student student);
	
	public void delete(Integer id);
	public int deleteAll();
	
	public void close();
}
