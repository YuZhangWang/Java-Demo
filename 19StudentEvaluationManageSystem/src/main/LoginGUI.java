package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI {

    // 设置运行时窗口的大小
    Dimension faceSize = new Dimension(350, 150);
    // 获得屏幕的大小
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame jf;
    //水平box
    private Box center = Box.createVerticalBox();
    //学号的JPanel
    private JPanel idPanel = new JPanel();
    //密码的JPanel
    private JPanel passwordPanel = new JPanel();
    private JLabel lUserId = new JLabel("学         号");
    private JTextField tUserId = new JTextField(15);
    private JLabel lPassword = new JLabel("密         码");
    private JPasswordField tPassword = new JPasswordField(15);
    //按钮的JPanel
    private JPanel buttonPanel = new JPanel();
    private JButton bLogin = new JButton("登录");
    private JButton bCancel = new JButton("取消");

    public static void main(String[] args) throws Exception {
        new LoginGUI().init();
    }

    public void init() {

        jf = new JFrame();
        //设置JFrame的名称
        jf.setTitle("登录");

        //将lUserId，tUserId放在idPanel中，idPanel默认水平放置
        idPanel.add(lUserId);
        idPanel.add(tUserId);

        passwordPanel.add(lPassword);
        passwordPanel.add(tPassword);

        //水平box加入学号和密码的JPanel
        center.add(idPanel);
        center.add(passwordPanel);

        buttonPanel.add(bLogin);
        buttonPanel.add(bCancel);

        //登录按钮的监听器
        bLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = tUserId.getText();
                String password = String.valueOf(tPassword.getPassword());

                //开启接受数据的线程
                if (userId.trim().equals("") || userId == null ||
                        password.trim().equals("") || password == null) {
                    JOptionPane.showMessageDialog(jf, "用户名或密码不能为空！", "提示",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    //这里是登录时执行的操作
                    new StudentManageView().init();
                    new Assessment_Test().init();
                    new Course_Test().init();
                }
            }
        });

        //取消按钮的监听器
        bCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        });

        center.add(buttonPanel);

        jf.add(center);
        jf.pack();

        //设置JFame运行时的大小
        jf.setSize(faceSize);
        //设置JFame运行时的位置
        jf.setLocation((int) (screenSize.width - faceSize.getWidth()) / 2,
                (int) (screenSize.height - faceSize.getHeight()) / 2);
        //设置JFame不可最大化
        jf.setResizable(false);
        //设置JFrame点击X时结束程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置JFrame可见
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void loginFailure() {
        JOptionPane.showMessageDialog(jf, "用户名或密码或用户类型不正确！", "提示",
                JOptionPane.WARNING_MESSAGE);
    }
}

