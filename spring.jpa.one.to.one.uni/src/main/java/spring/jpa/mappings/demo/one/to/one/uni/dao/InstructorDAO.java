package spring.jpa.mappings.demo.one.to.one.uni.dao;

import spring.jpa.mappings.demo.one.to.one.uni.entity.Instructor;

public interface InstructorDAO {
	public void save(Instructor instructor);
	public Instructor findInstructorById(Integer id);
	public void deleteInstructorById(Integer id);
}
