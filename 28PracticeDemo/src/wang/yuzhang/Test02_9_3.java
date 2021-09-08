package wang.yuzhang;

import java.util.Scanner;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/3 16:32
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Test02_9_3 {
    public static void main(String[] args) {
        int flag = 1;
        while (true) {
            f3();
            Scanner Judge = new Scanner(System.in);
            System.out.println("是否继续输入?(输入1继续,输入0退出)");
            flag = Judge.nextInt();
            if (flag == 0) {
                break;
            } else if (flag == 1) {
                continue;
            }
        }
    }

    public static void f3() {
        Scanner input = new Scanner(System.in);
        System.out.println("输入年份和月份:");
        int Year = input.nextInt();
        int Month = input.nextInt();
        switch (Month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("31");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("30");
                break;
            case 2:
                if ((Year % 400 == 0) || (Year % 4 == 0 && Year % 100 != 0)) {
                    System.out.println("29");
                } else {
                    System.out.println("28");
                }
        }
    }
}
