package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class StudentService {

    private Student[] student = new Student[50];

    // 添加学生信息
    public void insert(Student s) {

        for (int i = 0; i < student.length; i++) {
            if (student[i] == null) {
                student[i] = s;
                System.out.println("添加成功！");
                break;
            }
        }
    }

    // 删除学生信息
    public int delete(String userId) {
        int flag = 0;

        for (int i = 0; i < student.length; i++) {
            if (student[i] != null) {
                if (student[i].getUserId().equals(userId)) {
                    student[i] = null;
                    System.out.print("删除成功！");
                    flag = 1;
                    break;
                }
            }
        }

        return flag;
    }

    // 修改学生信息
    public void update(Student s) {

        String userId = s.getUserId();

        for (int i = 0; i < student.length; i++) {
            if (student[i] != null) {
                if (student[i].getUserId().equals(userId)) {

                    student[i].setName(s.getName());
                    student[i].setSex(s.getSex());
                    student[i].setAge(s.getAge());
                    student[i].setPhone(s.getPhone());
                    student[i].setQq(s.getQq());
                    student[i].setEmail(s.getEmail());
                }
            }
        }
    }

    // 按学号查询
    public Student selectById(String userId) {

        for (Student s : student) {
            if (s != null) {
                if (s.getUserId().equals(userId)) {
                    return s;
                }
            }
        }
        return null;
    }

    // 查询所有学生信息
    public Student[] selectAll() {
        return student;
    }
}
