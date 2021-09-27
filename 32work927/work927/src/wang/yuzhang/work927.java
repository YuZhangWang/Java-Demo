package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/27 19:13
 * @link: https://www.yuzhang.wang/
 ***********************************/

import java.io.File;

/**
 * Description: 类的描述
 * Author: xch
 * Date: 2021/09/27 17:34
 * Version: 1.0 版本信息
 */
public class work927 {
    public static void main(String[] args) {
        String s1 = "D:/Game";
        showFiles(s1);
        System.out.println("---------------");
        File f = new File("D:/Game");
        showFiles(f);

    }

    // 输出 指定文件夹中的所有内容(只要该文件夹的子文件和子目录)的绝对路径
    private static void showFiles(String path) {
        File f1 = new File(path);
        File[] list1 = f1.listFiles();
        for (int i = 0; i < list1.length; i++) {
            System.out.println(list1[i].getAbsolutePath());
        }

    }

    // 输出 指定文件夹中的所有内容(子目录、子目录里面的等等)的绝对路径
    private static void showFiles(File file) {
        File[] list2 = file.listFiles();
        if (list2 != null && list2.length > 0) {
            for (int i = 0; i < list2.length; i++) {
                System.out.println(list2[i].getAbsolutePath());
                if (list2[i] != null) {
                    showFiles(list2[i]);
                } else {
                    break;
                }
            }
        }
    }
}
