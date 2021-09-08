package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/3 17:20
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Test03_9_3 {
    public static void main(String[] args) {
        f4();
    }

    public static void f4() {
        int sum = 0;
        int i = 1;
        while (i <= 100) {
            if (i % 2 == 1) {
                sum += i;
            }
            i++;
            System.out.println(sum);
        }
    }
}