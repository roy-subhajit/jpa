package spring.jpa.mappings.one.to.many.dao;

import spring.jpa.mappings.one.to.many.entity.Course;
import spring.jpa.mappings.one.to.many.entity.Student;

public interface InstructorDAO {
	public void saveCourse(Course course);
	public Course findCourseAndStudentByCourseId(Integer id);
	public Student findStudentAndCourseByStudentId(Integer id);
	public void saveStudent(Student student);
	public void deleteCourseById(Integer id);
	public void deleteStudentById(Integer id);
}
