package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/28 22:37
 * @link: https://www.yuzhang.wang/
 ***********************************/

import java.io.*;

/**
 * @author cz
 * @version 1.0
 * @describe test1
 * @date 2021/09/28 18:12
 */
public class splitFile {
    public static void main(String[] args) throws IOException {
//       splitFile("src/t1/t1/t2/a.txt", 4);
        mergeFile("src/wnag/yu/zhang/a.txt_0");
    }

    /**
     * 把指定的文件拆分成多个小文件, 单个小文件最少 1 个byte,
     * 小文件的名称 在原文件名的后, 追加 "_" 和 该小文件的序号,
     * 例如 a.txt 分3份 --> a.txt_0, a.txt_1, a.txt_2
     *
     * @param path  文件路径
     * @param count 小文件个数(要能够对原文件进行拆分的)
     */
    public static void splitFile(String path, int count) {
        int l = 0;
        for (int i = 0; i < count; i++) {
            try (
                    FileInputStream fis = new FileInputStream(path);
                    FileOutputStream fos = new FileOutputStream(path + "_" + i)
            ) {
                byte[] buffer = new byte[1024];
                int length = fis.read(buffer);
                if (length < count)
                    throw new Exception("文件太小了,不够分成" + count + "个子文件");
                if (i == count - 1)
                    fos.write(buffer, l, length -= l);
                else
                    fos.write(buffer, l, length /= count);
                l += length;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("拆分完成");
    }

    /**
     * 把多个小文件合并成一个大文件, 必须是由同一个大文件拆分来的小文件。
     * 例如 a.txt 分3份 --> a.txt_0, a.txt_1, a.txt_2 。
     * 此处 传入 a.txt_0, a.txt_1, a.txt_2 都可以
     *
     * @param path 小文件名
     */
    public static void mergeFile(String path) {
        String[] strs = path.split("_");
        int count = 0;
        while (true) {
            if (new File(strs[0] + "_" + count).exists())
                count++;
            else
                break;
        }
        for (int i = 0; i < count; i++) {
            try (
                    FileInputStream fis = new FileInputStream(new File(strs[0] + "_" + i));
                    FileOutputStream fos = new FileOutputStream("src/wnag/yu/zhang/a.txt", true)
            ) {
                byte[] bytes = new byte[1024];
                int length = -1;
                while ((length = fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, length);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("合并完成");
    }
}
