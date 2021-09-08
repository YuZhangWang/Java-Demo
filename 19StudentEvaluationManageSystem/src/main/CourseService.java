package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CourseService {

    private List<Course> courseList = new ArrayList<Course>();

    public void insert(Course course) {
        courseList.add(course);
    }

    public int delete(String id) {
        int i = 0;
        int flag = 0;


        //用迭代器遍历courseList
        Iterator<Course> it = courseList.iterator();

        Course course = null;

        while (it.hasNext()) {
            course = it.next();
            if (course.getId().equals(id)) {
                courseList.remove(i);
                flag = 1;
                break;
            }
            i++;
        }

        return flag;
    }

    public int update(Course course) {

        String id = course.getId();

        int i = 0;
        int flag = 0;

        Iterator<Course> it = courseList.iterator();

        while (it.hasNext()) {
            course = it.next();
            if (course.getId().equals(id)) {
                courseList.remove(i);
                courseList.add(i, course);

                flag = 1;
            }
            i++;
        }

        return flag;
    }

    public List<Course> select(Course course) {
        String id = course.getId();
        String grade = course.getGrade();
        String term = course.getTerm();

        List<Course> selectList = new ArrayList<Course>();

        for (Course cour : courseList) {
            if ((id.trim().equals("") || cour.getId().equals(id))
                    && cour.getGrade().equals(grade)
                    && (term.equals("0") || cour.getTerm().equals(term))) {
                selectList.add(cour);
            }
        }

        return selectList;
    }

}
