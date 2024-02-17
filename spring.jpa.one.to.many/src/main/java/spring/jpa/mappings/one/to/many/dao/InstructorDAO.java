package spring.jpa.mappings.one.to.many.dao;

import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Instructor;

public interface InstructorDAO {
	public void save(Instructor instructor); 
	public Instructor findInstructorById(Integer id);
	public void update(Instructor instructor);
	public Course findCourseById(Integer id);
	public void updateCourse(Course course);
	public void deleteInstructorById(Integer id);
	public void deleteCourseById(Integer id);
}
