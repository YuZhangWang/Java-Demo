package main;

import java.util.Scanner;

class CUI {
    //一级菜单（用户管理，学生管理，课程管理，成绩管理，综合排名管理，退出系统）
    private final static int FIRST_MENU_USER = 1;
    private final static int FIRST_MENU_STUDENT = 2;
    private final static int FIRST_MENU_COURSE = 3;
    private final static int FIRST_MENU_GRADE = 4;
    private final static int FIRST_MENU_SORT = 5;
    private final static int FIRST_MENU_EXIT = 6;
    //用户管理二级菜单（用户注册，用户登录，用户注销，返回）
    private final static int SECOND_USER_REGISTER = 1;
    private final static int SECOND_USER_LOGIN = 2;
    private final static int SECOND_USER_LOGOFF = 3;
    private final static int SECOND_USER_RETURN = 4;
    //学生管理二级菜单（）
    private final static int SECOND_STUDENT_ADD = 1;
    private final static int SECOND_STUDENT_DELETE = 2;
    private final static int SECOND_STUDENT_MODIFY = 3;
    private final static int SECOND_STUDENT_SEARCH = 4;
    private final static int SECOND_STUDENT_RETURN = 5;

    private static void soft_title() {
        System.out.println("------------------------------------");
        System.out.println("--       欢迎使用学生管理系统        --");
        System.out.println("------------------------------------");
        System.out.println("------------------------------------");
    }

    private static void mainMenu() {
        soft_title();
        System.out.println("|            1.用户管理              |");
        System.out.println("|            2.学生管理              |");
        System.out.println("|            3.课程管理              |");
        System.out.println("|            4.成绩管理              |");
        System.out.println("|            5.综合排名管理           |");
        System.out.println("|            6.退出系统              |");
        System.out.println("------------------------------------");
        System.out.println("系统已启动，请输入功能编号1-6：");
    }

    public static void menu_First_User() {
        System.out.println("------------------------------------");
        System.out.println("|             1.用户注册             |");
        System.out.println("|             2.用户登录             |");
        System.out.println("|             3.用户注销             |");
        System.out.println("|             4.返回                 |");
        System.out.println("------------------------------------");
        System.out.println("目前在一级菜单(用户管理)下，请输入功能编号1-4：");
    }

    public void test() {
        int choice; //一级菜单的编号
        int sechoice; //二级菜单的编号
        boolean nobackflag = true; //二级菜单返回到一级菜单的信号
        boolean backFirst = true; //判断是否继续执行一级菜单
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用本系统，系统启动中...");
        while (backFirst) {
            mainMenu();
            choice = Integer.parseInt(scanner.nextLine());
            nobackflag = true;
            switch (choice) {
                case FIRST_MENU_USER:
                    //用户管理
                    while (nobackflag) {
                        menu_First_User();
                        sechoice = Integer.parseInt(scanner.nextLine());
                        switch (sechoice) {
                            case SECOND_USER_REGISTER:
                                User.register();
                                break;
                            case SECOND_USER_LOGIN:
                                User.login();
                                break;
                            case SECOND_USER_LOGOFF:
                                User.logout();
                                break;
                            case SECOND_USER_RETURN:
                                nobackflag = false;
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case FIRST_MENU_STUDENT:
                    //学生管理
                    /*
                    while (nobackflag) {

                        nobackflag = new Stu_Manage().execute(nobackflag);
                    } */
                    new StudentManageView().init();
                    break;

                case FIRST_MENU_COURSE:
                    //课程管理
                    new Course_Test().init();
                    break;
                case FIRST_MENU_GRADE:
                    //成绩管理
                    /*
                    while (nobackflag) {
                        nobackflag = new Score_Manage().execute(nobackflag);
                    }
                    */
                    new CourseView().init();
                    break;

                case FIRST_MENU_SORT:
                    //综合排名管理
                    new Assessment_Test().init();
                    break;

                case FIRST_MENU_EXIT:
                    //退出系统
                    System.out.println("系统已退出");
                    scanner.close();
                    backFirst = false; //系统退出，不再执行一级菜单
                    return;
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }

}

class User {
    //用户登录
    public static void login() {
        String userType = null; //用户类型
        String userName = null; //用户名
        String password = null; //密码
        Scanner in = new Scanner(System.in);
        System.out.println("请选择用户类型：");
        System.out.println("1.在校学生");
        System.out.println("2.任课老师");
        System.out.println("3.系统管理员");
        userType = in.nextLine();
        while (!userType.equals("1") && !userType.equals("2") && !userType.equals("3")) {
            System.out.println("用户类型不对，请重新输入！");
            userType = in.nextLine();
        }
        System.out.println("请输入用户名：");
        userName = in.nextLine();
        System.out.println("请输入密码：");
        password = in.nextLine();
        if (userType.equals("1") && userName.equals("stu")
                && password.equals("stu")) {
            System.out.println("学生操作界面");
            new LoginGUI().init();
            System.out.println("Press any key to continue!");
            in.nextLine();
        } else if (userType.equals("2") && userName.equals("tea")
                && password.equals("tea")) {
            System.out.println("教师操作界面");
            new LoginGUI().init();
            System.out.println("Press any key to continue!");
            in.nextLine();
        } else if (userType.equals("3") && userName.equals("admin")
                && password.equals("admin")) {
            System.out.println("管理员操作界面");
            new LoginGUI().init();
            System.out.println("Press any key to continue!");
            in.nextLine();
        } else {
            System.out.println("登录失败，用户类型，用户名或密码不对！");

            System.out.println("Press any key to continue!");
            in.nextLine();
        }
    }

    // 用户注册
    public static void register() {
        System.out.println("--------------开始注册---------------");
        System.out.println("------------------------------------");
        Student stu; //存储注册后的学生信息
        User_Register reg = new User_Register();
        stu = reg.register();
        if (stu != null) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }
    }

    // 用户注销
    public static void logout() {
        System.out.println("用户已注销！");
    }
}

public class MainCUI {
    public static void main(String[] args) {
        CUI cui = new CUI();
        cui.test();
    }
}
