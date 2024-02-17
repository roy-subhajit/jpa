package spring.jpa.mappings.demo.one.to.one.bi.dao;

import spring.jpa.mappings.demo.one.to.one.bi.entity.InstructorDetail;

public interface InstructorDAO {
	public InstructorDetail findInstructorDetailById(Integer id);
	public void deleteInstuctorDetailById(Integer id);
	public void deleteInstuctorDetailOnlyById(Integer id);
}
