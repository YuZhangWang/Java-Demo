package wang.yuzhang;

import java.util.Scanner;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/3 16:32
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Test01_9_3 {
    public static void main(String[] args) {
        int flag_01 = 1;
        while (true) {
            f1();
            Scanner Judge = new Scanner(System.in);
            System.out.println("是否继续输入?(输入1继续,输入0退出)");
            flag_01 = Judge.nextInt();
            if (flag_01 == 0) {
                break;
            } else if (flag_01 == 1) {
                continue;
            }
        }

        int flag_02 = 1;
        while (true) {
            f2();
            Scanner Judge = new Scanner(System.in);
            System.out.println("是否继续输入?(输入1继续,输入0退出)");
            flag_02 = Judge.nextInt();
            if (flag_02 == 0) {
                break;
            } else if (flag_02 == 1) {
                continue;
            }
        }
    }


    public static void f1() {
        Scanner input_01 = new Scanner(System.in);
        System.out.println("请输入英语分数:");
        int English = input_01.nextInt();
        if (English >= 0 && English < 60) {
            System.out.println("D");
        } else if (English >= 60 && English < 80) {
            System.out.println("C");
        } else if (English >= 80 && English < 90) {
            System.out.println("B");
        } else if (English >= 90 && English <= 100) {
            System.out.println("A");
        } else {
            System.out.println("分数不合法!");
        }
    }


    public static void f2() {
        Scanner input_02 = new Scanner(System.in);
        System.out.println("请输入语文数学分数,并以空格隔开:");
        int Chinese = input_02.nextInt();
        int Math = input_02.nextInt();
        if (Chinese >= 60 && Math >= 60) {
            System.out.println("都及格了");
        } else if (Chinese >= 60 & Math < 60) {
            System.out.println("只需补考数学");
        } else if (Math >= 60 && Chinese < 60) {
            System.out.println("只需补考语文");
        } else if (Chinese < 60 && Math < 60) {
            System.out.println("语文数学都需要补考");
        }
    }
}