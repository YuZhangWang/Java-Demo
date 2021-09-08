package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements ICourseDao {

    private static final String SQL_INSERT = "insert into course (name,mark,grade,term) values(?,?,?,?)";
    private static final String SQL_DELETE = "delete from course where id=?";
    private static final String SQL_UPDATE = "update course set name=?,mark=?,grade=?,term=? where id=?";

    public int insert(Course course) throws Exception {
        return update(SQL_INSERT, new Object[]{course.getName(), course.getMark(),
                course.getGrade(), course.getTerm()});
    }

    public int delete(String id) throws Exception {
        return update(SQL_DELETE, new Object[]{id});
    }

    @Override
    public int update(Course course) throws Exception {
        return update(SQL_UPDATE, new Object[]{course.getName(), course.getMark(),
                course.getGrade(), course.getTerm(), course.getId()});
    }

    public List select(Course course) throws Exception {

        List courseList = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select from course where 1= 1";

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            if (course.getId().trim().equals("")) {
                sql += " and id = " + course.getId();
            }

            if (course.getTerm().equals("无")) {
                sql += " and term = " + course.getTerm();
            }

            if (course.getGrade().trim().equals("无")) {
                sql += " and grade = " + course.getGrade();
            }

            rs = pstmt.executeQuery();
            courseList = new ArrayList<Course>();
            while (rs.next()) {
                Course cour = new Course();
                cour.setId(String.valueOf(rs.getInt("id")));
                cour.setName(String.valueOf(rs.getString("name")));
                cour.setMark(String.valueOf(rs.getDouble("mark")));
                cour.setGrade(String.valueOf(rs.getInt("grade")));
                cour.setTerm(String.valueOf(rs.getInt("term")));
                courseList.add(cour);
            }

            return courseList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
    }

    public int update(String sql, Object[] params) throws Exception {
        int flag = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 1; i < params.length + 1; i++) {
                    pstmt.setObject(i, params[i - 1]);
                }
            }

            pstmt.executeUpdate();

            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.close(pstmt, conn);
        }

        return flag;
    }
}

