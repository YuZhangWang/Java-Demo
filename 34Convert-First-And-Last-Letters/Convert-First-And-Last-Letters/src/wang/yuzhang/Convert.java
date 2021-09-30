package wang.yuzhang;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/29 22:44
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Convert {
    public static void main(String[] args) {

        test();
    }


    private static void test() {
           /*
        提示用户输入一段英文(全部小写), 将英文单词的首尾字母转大写后逐行保存到 aa.txt 中
        例如:
            输入:
            this is a dog
            保存的内容:
            ThiS
            IS
            A
            DoG
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入小写字母的字符串：");
        String s1 = sc.nextLine();
        char[] s2 = s1.toCharArray();
        s2[0] = Character.toUpperCase(s2[0]);
        s2[s2.length - 1] = Character.toUpperCase(s2[s2.length - 1]);
        for (int i = 0; i < s2.length; i++) {
            if (s2[i] == ' ') {
                s2[i - 1] = Character.toUpperCase(s2[i - 1]);
                s2[i + 1] = Character.toUpperCase(s2[i + 1]);
            }
        }
        String[] s3 = String.valueOf(s2).split(" ");
        try (
                FileWriter writer = new FileWriter("src/aa.txt", true);
        ) {
            for (String s4 : s3) {
                writer.write(s4);
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
