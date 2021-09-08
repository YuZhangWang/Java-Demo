package main;

import java.util.Scanner;
import java.util.regex.Pattern;

interface IScoreService {

    public void insert();

    public void delete();

    public void update();

    public void select();

}

class Score {

    private String id;//学号
    private String name;//姓名
    private String chinese;//语文
    private String math;//数学
    private String english;//英语
    private String year;//年份
    private String term;//学期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}

class ScoreServiceImp implements IScoreService {

    private Score[] score = new Score[50];
    private Scanner in = new Scanner(System.in);

    //添加，在这里就不进行检查各种输入是否符合要求
    public void insert() {
        String id = null;
        String name = null;
        String chinese = null;
        String math = null;
        String english = null;
        String year = null;
        String term = null;

        System.out.println("请输入学号：");
        id = in.nextLine();
        System.out.println("请输入姓名:");
        name = in.nextLine();
        System.out.println("请输入语文:");
        chinese = in.nextLine();
        System.out.println("请输入数学:");
        math = in.nextLine();
        System.out.println("请输入英语:");
        english = in.nextLine();
        System.out.println("请输入年份:");
        year = in.nextLine();
        System.out.println("请选择学期:");
        System.out.println("1.第一学期:");
        System.out.println("2.第二学期:");
        term = in.nextLine();

        Score s = new Score();
        s.setId(id);
        s.setName(name);
        s.setChinese(chinese);
        s.setMath(math);
        s.setEnglish(english);
        s.setYear(year);
        s.setTerm(term);

        for (int i = 0; i < score.length; i++) {
            if (score[i] == null) {
                score[i] = s;
                System.out.println("添加成功！");
                break;
            }
        }
    }

    //删除，在这里就不进行检查各种输入是否符合要求
    public void delete() {
        String id = null;
        String year = null;
        String term = null;
        System.out.println("请输入学号：");
        id = in.nextLine();
        System.out.println("请输入年份：");
        year = in.nextLine();
        System.out.println("请选择学期：");
        term = in.nextLine();

        for (int i = 0; i < score.length; i++) {
            if (score[i] != null) {
                if (score[i].getId().equals(id)
                        && score[i].getYear().equals(year)
                        && score[i].getTerm().equals(term)) {
                    score[i] = null;
                    System.out.println("删除成功！");
                    break;
                }
            }
        }
    }

    //修改，在这里就不进行检查各种输入是否符合要求
    public void update() {
        String id = null;
        String chinese = null;
        String math = null;
        String english = null;
        String year = null;
        String term = null;

        System.out.println("请输入学号：");
        id = in.nextLine();
        System.out.println("请输入年份：");
        year = in.nextLine();
        System.out.println("请选择学期：");
        term = in.nextLine();

        int flag = 0;

        for (int i = 0; i < score.length; i++) {
            if (score[i] != null) {
                //检查是否是要修改的学习成绩
                if (score[i].getId().equals(id)
                        && score[i].getYear().equals(year)
                        && score[i].getTerm().equals(term)) {
                    System.out.println("请输入语文：");
                    chinese = in.nextLine();
                    System.out.println("请输入数学：");
                    math = in.nextLine();
                    System.out.println("请选择英语：");
                    english = in.nextLine();
                    score[i].setChinese(chinese);
                    score[i].setMath(math);
                    score[i].setEnglish(english);
                    System.out.println("修改成功！");
                    flag = 1;
                    break;
                }
            }
        }

        if (flag == 0) {
            System.out.println("不存在该课程成绩！");
        }
    }

    //查询，在这里就不进行检查各种输入是否符合要求
    public void select() {
        System.out.println("学号\t\t姓名\t语文\t数学\t英语\t年份\t学期");

        for (int i = 0; i < score.length; i++) {
            if (score[i] != null) {
                System.out.println(score[i].getId() + "\t" + score[i].getName() + "\t"
                        + score[i].getChinese() + "\t" + score[i].getMath() + "\t"
                        + score[i].getEnglish() + "\t" + score[i].getYear() + "\t"
                        + score[i].getTerm());
            }
        }
    }
}

public class Score_Manage {
    private IScoreService service = new ScoreServiceImp();

    public boolean execute(boolean nobackflag) {

        Scanner in = new Scanner(System.in);

        String option = null;
        String pattern = "[1-5]";

        while (nobackflag) {
            System.out.println("------------------------------------");
            System.out.println("|            1.添加学习成绩          |");
            System.out.println("|            2.删除学习成绩          |");
            System.out.println("|            3.修改学习成绩          |");
            System.out.println("|            4.查询学习成绩          |");
            System.out.println("|            5.返回上级菜单          |");
            System.out.println("------------------------------------");
            System.out.println("输入功能编号(1-5)");
            option = in.nextLine();

            while (!Pattern.matches(pattern, option)) {
                System.out.println("输入有误！请重新输入！");
                option = in.nextLine();
            }

            switch (Integer.valueOf(option)) {
                case 1:
                    service.insert();
                    break;
                case 2:
                    service.delete();
                    break;
                case 3:
                    service.update();
                    break;
                case 4:
                    service.select();
                    break;
                case 5:
                    nobackflag = false;
                    break;
            }
        }
        return nobackflag;
    }
}
