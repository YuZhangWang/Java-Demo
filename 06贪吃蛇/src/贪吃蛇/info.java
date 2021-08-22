package 贪吃蛇;
//info的窗口
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Info extends Frame{
    public void infoInit(int x,int y,int height) {
        this.setBounds(x+height, y+height/2, height/2,height/2 );
        this.setName("关于");
        this.addWindowListener(new WindowAdapter() {
            //重写closing方法
            public void windowClosing(WindowEvent e) {
                Info.this.setVisible(false);//close();     //匿名内部类也是内部类，可以直接调用外部类的方法和成员
            }
        });
        this.setVisible(true);
        this.repaint();  //paint方法只能是系统调用，我们只能通过repaint来间接调用paint
    }
    //这里其实应该用一个好的图片（带帮助）直接画上最好
    public void paint(Graphics g) {
        g.drawString("作者：CP", 50    , 50);
    }

}