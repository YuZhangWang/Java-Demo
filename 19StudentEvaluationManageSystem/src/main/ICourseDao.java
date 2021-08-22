package main;

import java.util.List;

public interface ICourseDao {
	public int insert(Course course)throws Exception ;

	public int delete(String id)throws Exception ;

	public int update(Course course)throws Exception ;

	public List<Course> select(Course course)throws Exception ;
}
