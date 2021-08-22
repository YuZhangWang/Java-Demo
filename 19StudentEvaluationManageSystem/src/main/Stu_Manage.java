package main;

import java.util.Scanner;
import java.util.regex.Pattern;

class StudentManage {

    //用Student的数组存储学生信息
    private Student[] student = new Student[50];

    // 添加学生信息
    public void insert() {

        Student s = new User_Register().register();

        //依次查询student数组，如果某个数组成员为空，说明成员没有占用
        for (int i = 0; i < student.length; i++) {
            if (student[i] == null) {
                student[i] = s;
                System.out.println("添加成功！");
                break;
            }
        }
    }

    // 删除学生信息
    public void delete() {
        Scanner in = new Scanner(System.in);
        String userId = null;
        //用于标记是否删除
        int flag = 0;
        //用于标记是否继续删除
        userId = null;
        System.out.print("请输入要删除学生的学号(输入0退出)：");
        userId = in.nextLine();
        while (!userId.equals("0")) {
            for (int i = 0; i < student.length; i++) {
                // 首先检查student[i]是否为空，否则执行下面语句会出现运行时异常
                if (student[i] != null) {
                    //如果该学生的学号与要删除的学号一致，就将student[i] = null
                    if (student[i].getUserId().equals(userId)) {
                        student[i] = null;
                        System.out.print("删除成功！");
                        flag = 1;
                        break;
                    }
                }
            }

            if (flag == 0) {
                System.out.print("删除失败，不存在该学号对应的学生！");
            }

            flag = 0;

            System.out.print("请输入要删除学生的学号(输入0退出)：");
            userId = in.nextLine();
        }
    }

    // 修改学生信息
    public void update() {
        Scanner in = new Scanner(System.in);

        String userId = null;
        String name = null;
        String sex = null;
        String age = null;
        String phone = null;
        String qq = null;
        String email = null;

        System.out.println("请输入修改学生的学号：");
        userId = in.nextLine();
        int flag = 0;

        for (int i = 0; i < student.length; i++) {
            // 首先检查student[i]是否为空，否则执行下面语句会出现运行时异常
            if (student[i] != null) {
                //如果该学生的学号与要删除的学号一致，说明该student[i]就是要修改的学生信息
                if (student[i].getUserId().equals(userId)) {
                    System.out.println("已找到！旧学生信息如下：");
                    System.out.println("学号：" + student[i].getUserId());
                    System.out.println("姓名：" + student[i].getName());
                    System.out.println("性别：" + student[i].getSex());
                    System.out.println("年龄：" + student[i].getAge());
                    System.out.println("手机号：" + student[i].getPhone());
                    System.out.println("qq号：" + student[i].getQq());
                    System.out.println("email：" + student[i].getEmail());

                    //依次输入修改后的信息
                    System.out.println("请输入修改后的姓名：");
                    name = in.nextLine();
                    System.out.println("请输入修改后的性别：");
                    sex = in.nextLine();
                    System.out.println("请输入修改后的年龄：");
                    age = in.nextLine();
                    System.out.println("请输入修改后的手机号：");
                    phone = in.nextLine();
                    System.out.println("请输入修改后的qq：");
                    qq = in.nextLine();
                    System.out.println("请输入修改后的email：");
                    email = in.nextLine();

                    student[i].setName(name);
                    student[i].setSex(sex);
                    student[i].setAge(age);
                    student[i].setPhone(phone);
                    student[i].setQq(qq);
                    student[i].setQq(qq);
                    student[i].setEmail(email);

                    flag = 1;

                    System.out.print("修改成功！");

                    break;
                }
            }
        }

        if (flag == 0) {
            System.out.print("修改失败，不存在该学号对应的学生！");
        }
    }

    // 查询学生信息
    public void query() {

        Scanner in = new Scanner(System.in);

        String option = null;
        String pattern = "[1-3]";

        //用于标记是否退出
        int flag = 0;

        while (flag == 0) {
            System.out.println("------------------------------------");
            System.out.println("|             1.按学号查找           |");
            System.out.println("|             2.查找所有学生         |");
            System.out.println("|             3.退出                |");
            System.out.println("------------------------------------");

            option = in.nextLine();

            //检查输入的命令是否是1-3。不是，则重新输入
            while (!Pattern.matches(pattern, option)) {
                System.out.println("请重新选择：");
                option = in.nextLine();
            }

            switch (Integer.valueOf(option)) {
                case 1:
                    selectById();
                    break;
                case 2:
                    selectAll();
                    break;
                case 3:
                    flag = 1;
                    break;
            }
        }
    }

    // 按学号查询
    public void selectById() {
        Scanner in = new Scanner(System.in);

        String userId = null;
        System.out.println("请输入学号：");
        userId = in.nextLine();

        //依次遍历student中的每个元素
        for (Student s : student) {
            if (s != null) {
                if (s.getUserId().equals(userId)) {
                    System.out.println("学号：" + s.getUserId());
                    System.out.println("姓名：" + s.getName());
                    System.out.println("性别：" + s.getSex());
                    System.out.println("年龄：" + s.getAge());
                    System.out.println("手机号：" + s.getPhone());
                    System.out.println("qq号：" + s.getQq());
                    System.out.println("email：" + s.getEmail());
                    break;
                }
            }
        }
    }

    // 查询所有学生信息
    public void selectAll() {
        System.out.println("学号\t\t" + "姓名\t" + "性别\t" + "年龄\t" + "手机号\t\t"
                + "qq号\t\t" + "email\t");
        for (Student s : student) {
            if (s != null) {
                System.out.print(s.getUserId() + "\t");
                System.out.print(s.getName() + "\t");
                System.out.print(s.getSex() + "\t");
                System.out.print(s.getAge() + "\t");
                System.out.print(s.getPhone() + "\t");
                System.out.print(s.getQq() + "\t");
                System.out.println(s.getEmail() + "\t");
            }

        }
    }

}

public class Stu_Manage {
    private StudentManage studentManage = new StudentManage();

    public boolean execute(boolean nobackflag) {
        Scanner in = new Scanner(System.in);

        String option = null;
        String pattern = "[1-5]";
        while (nobackflag) {
            System.out.println("------------------------------------");
            System.out.println("|           1.添加学生信息           |");
            System.out.println("|           2.删除学生信息           |");
            System.out.println("|           3.修改学生信息           |");
            System.out.println("|           4.查询学生信息           |");
            System.out.println("|           5.进入GUI界面            |");
            System.out.println("|           6.返回上级菜单           |");
            System.out.println("------------------------------------");
            System.out.println("目前在一级菜单(学生管理)下，请输入功能编号1-6：");

            option = in.nextLine();
            while (!Pattern.matches(pattern, option)) {
                System.out.println("请重新选择：");
                option = in.nextLine();
            }

            switch (Integer.valueOf(option)) {
                case 1:
                    studentManage.insert();
                    break;
                case 2:
                    studentManage.delete();
                    break;
                case 3:
                    studentManage.update();
                    break;
                case 4:
                    studentManage.query();
                    break;
                case 5:
                    new StudentManageView().init();
                case 6:
                    nobackflag = false;
                    break;
                default:
                    break;
            }
        }
        return nobackflag;
    }
}
