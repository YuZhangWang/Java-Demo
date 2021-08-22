package main;

import java.util.List;

public class CourseServiceImp implements ICourseService {

	ICourseDao dao = new CourseDaoImpl();

	public int insert(Course course) throws Exception{
		return dao.insert(course);
	}

	public int delete(String id) throws Exception {
		return dao.delete(id);
	}

	public int update(Course course) throws Exception {
		return dao.update(course);
	}

	public List select(Course course) throws NumberFormatException, Exception{
		return dao.select(course);
	}
}
