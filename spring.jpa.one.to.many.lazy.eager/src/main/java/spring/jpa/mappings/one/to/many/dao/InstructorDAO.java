package spring.jpa.mappings.one.to.many.dao;

import java.util.List;

import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Instructor;

public interface InstructorDAO {
	public Instructor findInstructorById(Integer id);
	public List<Course> findCoursesByInstructorId(Integer id);
	public Instructor findInstructorByIdUsingJoinFetch(Integer id);
	public Instructor findInstructorByIdUsingMultipleJoinFetch(Integer id);
}
