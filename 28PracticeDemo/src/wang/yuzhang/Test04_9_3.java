package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/3 17:23
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Test04_9_3 {
    public static void main(String[] args) {
        f5();
    }

    public static void f5() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; i < 10; j++) {
                if (i == j) {
                    System.out.println(j + "*" + i + "=" + i * j);
                    break;
                } else {
                    System.out.print(j + "*" + i + "=" + i * j + "  ");
                }
            }
        }
    }
}