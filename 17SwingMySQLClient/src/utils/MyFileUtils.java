package utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;


public class MyFileUtils {

    // 打开文件
    public File openFile(Frame frame) {
        String picPaht = " ";
        // 文件选择器

        File file = new File("");
        String filePath = "";
        try {
            filePath = file.getCanonicalPath();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // 设置文件显示类型为仅显示文件
        JFileChooser fileChooser = new JFileChooser(filePath);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // 文件过滤器
        JpgFilter jpg = new JpgFilter();
        BmpFilter bmp = new BmpFilter();
        PngFilter png = new PngFilter();
        GifFilter gif = new GifFilter();
        // 向用户可选择的文件过滤器列表添加一个过滤器。
        fileChooser.addChoosableFileFilter(jpg);
        fileChooser.addChoosableFileFilter(bmp);
        fileChooser.addChoosableFileFilter(png);
        fileChooser.addChoosableFileFilter(gif);
        // 返回当前的文本过滤器，并设置成当前的选择
        fileChooser.setFileFilter(fileChooser.getFileFilter());
        // 弹出一个 "Open File" 文件选择器对话框
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        // 得到选择文件的名字
        File fileName = fileChooser.getSelectedFile();
        System.out.println("Choose Imgae: " + fileName.getAbsolutePath());


        return fileName;

    }

    // 文件过滤
    class JpgFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            else {
                String name = f.getAbsolutePath();
                System.out.println("file name: " + name);
                if (name.endsWith(".jpg") || name.endsWith(".png"))
                    return true;
                else
                    return false;
            }

        }

        @Override
        public String getDescription() {
            return ".jpg";
        }

    }

    class BmpFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            return f.getName().endsWith(".bmp");
        }

        @Override
        public String getDescription() {
            return ".bmp";
        }

    }

    class GifFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            return f.getName().endsWith(".gif");
        }

        @Override
        public String getDescription() {
            return ".gif";
        }

    }

    class PngFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            return f.getName().endsWith(".png");
        }

        @Override
        public String getDescription() {
            return ".png";
        }

    }
}
