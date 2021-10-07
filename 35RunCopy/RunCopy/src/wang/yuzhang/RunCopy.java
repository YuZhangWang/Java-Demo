package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021-10-08 04:26
 * @link: https://www.yuzhang.wang/
 ***********************************/

import java.io.*;

public class RunCopy {
    public static void main(String[] args) {
        copyFile("src/test", "src");
    }

    /**
     * 把 指定目录中的 子文件, 复制到另一个目录中.
     * 只复制子文件
     *
     * @param oldPath 指定的目录路径
     * @param newPath 另一个目录路径
     */
    public static void copyFile(String oldPath, String newPath) {
        // 使用多线程, 提高复制效率
        // 推荐, 每一个文件使用一个线程去执行复制
        File f = new File(oldPath);
        File[] files = f.listFiles();//获得所有子文件路径
        if (!f.isDirectory()) {
            throw new RuntimeException("请输入文件夹的目录");
        }
        for (File file : files) {//将files中对象挨个赋给file
            if (file.isFile()) {//如果是文件就直接拷贝
                new MyRunCopy(file, newPath).start();//拷贝一个文件使用一个线程
            } else if (file.isDirectory()) {//如果是文件夹就递归一下
                copyFile(String.valueOf(file), newPath);//开始递归
            }
        }
    }
}

class MyRunCopy extends Thread {
    private File file;// 源文件路径
    private String newPath;// 目标文件路径

    public MyRunCopy(File file, String newPath) {
        super();
        this.file = file;
        this.newPath = newPath;
    }

    @Override
    public void run() {
        // 需要线程执行的任务
        System.out.println(this.getName() + "开始拷贝");
        try (
                FileInputStream fis = new FileInputStream(file);
        ) {
            File fCreat = new File(newPath, file.getName());
            if (!fCreat.exists()) {
                fCreat.createNewFile();//创建文件
            }
            FileOutputStream fos = new FileOutputStream(fCreat); // 初始化目标文件输出流
            byte[] buffer = new byte[fis.available()];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fis.close();
            fos.close(); //关闭输入输出流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + "拷贝完成");
    }
}