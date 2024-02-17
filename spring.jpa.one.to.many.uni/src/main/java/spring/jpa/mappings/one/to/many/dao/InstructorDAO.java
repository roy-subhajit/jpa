package spring.jpa.mappings.one.to.many.dao;

import spring.jpa.mappings.one.to.many.entity.Course;

public interface InstructorDAO {
	public void addCourseWithReviews(Course course);
	public Course fetchCourseWithReviewsByCourseId(Integer id);
	public void deleteCourseById(Integer id);
}
