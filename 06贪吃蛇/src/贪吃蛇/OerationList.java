package 贪吃蛇;

import java.awt.Choice;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class OerationList extends Panel {
    private Label l = new Label("时间：");
    private TextField time = new TextField(15);
    private Label l1 = new Label("得分:");
    private TextField score = new TextField("0", 15);
    private Label l2 = new Label("速度:");
    private Choice speed = new Choice();

    //private LocalTime lt = LocalTime.now();                //获取当前时间 ，java8最新时间包Time
    public OerationList listInit() {
        l.setFont(new Font("宋体", Font.PLAIN, 15));
        l1.setFont(new Font("宋体", Font.PLAIN, 15));
        l2.setFont(new Font("宋体", Font.PLAIN, 15));
        time.setFont(new Font("宋体", Font.PLAIN, 15));
        score.setFont(new Font("宋体", Font.PLAIN, 15));
        speed.setFont(new Font("宋体", Font.PLAIN, 15));
        //游戏速度选择框
        speed.add("一级");
        speed.add("二级");
        speed.add("三级");
        //时间框显示
        this.updateTime();
        //得分要根据吃食物数量来定
        //全部加到ol中
        this.add(l);
        this.add(time);
        this.add(l1);
        this.add(score);
        this.add(l2);
        this.add(speed);
        //this.setBounds((int)(grWidth*0.75), 0, (int)(grWidth*0.25), grHeight);
        this.setLayout(new GridLayout(6, 1, 0, 25));    //6行一列，水平间距50 ，垂直间距60
        this.setVisible(true);
        return this;
    }

    public String getScore() {
        return score.getText();
    }

    public String getSpeed() {
        return speed.getSelectedItem();
    }

    public void updateScore() {
        int i = Integer.parseInt(score.getText());
        i += 5;
        score.setText(String.valueOf(i));
    }

    public void updateTime() {
        time.setText(LocalTime.now().toString().split("\\.")[0]);
        //split是用正则表达式的 其中.表示任意字符  所以   .要用两个转义字符才能表示.本身
    }
}