package wang.yuzhang;

import java.util.Scanner;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/5 21:36
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Test05_9_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("输入的一个大于等于0的整数:");
        int num = input.nextInt();
        if (num < 0) {
            System.out.println("输入不合法");
            return;
        } else {
            f1(num);
        }
        f2();
    }

    public static void f1(int n) {
        System.out.print("倒序数字为:");
        while (n > 0) {
            int m = n % 10;
            n = n / 10;
            System.out.print(m);
        }
        System.out.println();
    }

    public static void f2() {
        System.out.println("三位数之间的水仙花数为:");
        for (int n = 100; n < 1000; n++) {
            int a = n / 100;
            int b = n / 10 % 10;
            int c = n % 10;
            if (a * a * a + b * b * b + c * c * c == n) {
                System.out.println(n);
            }
        }
    }
}
