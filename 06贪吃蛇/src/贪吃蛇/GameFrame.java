package 贪吃蛇;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import 贪吃蛇.Snake.OritentionEum;

//游戏的主窗口
public class GameFrame {
    private Frame f;             //其实GameFrame直接继承Frame窗口就好
    private int fLocationX = 300;
    private int fLocationY = 50;
    private int fWidth = 450;        //窗口宽
    private int fHeight = 450;        //窗口高
    private StartPanel sp;          //开始界面Panel
    private GameCanvas gc;        //贪吃蛇画布
    private OerationList ol;    //相关信息
    private int rate = 5;
    private Snake sk;
    private Food fd;

    public static void main(String[] args) {
        GameFrame game = new GameFrame();
        game.frameinit();
    }

    //窗口初始化
    public void frameinit() {
        f = new Frame("游戏窗口");
        f.setLayout(null);     //不使用布局管理，Panel用绝对定位
        sp = new StartPanel(this).panelInit();
        //sp =sp.panelInit();
        sp.setBounds(fWidth / 3, 100, fWidth / 3, fHeight / 2);        //这个是相对于sp的上一级的component的
        f.add(sp);
        this.setAtt(fLocationX, fLocationY);
    }

    //窗口的属性，单独拿出来，在切换到游戏运行时还要用
    public void setAtt(int x, int y) {
        if (f != null) {
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            f.setLocation(x, y);     //设置组件在桌面显示的位置
            f.setSize(fWidth, fHeight);        //设置组件的大小 ，也可用setBounds同时设置位置和大小
            f.setVisible(true);
        }
    }

    //游戏开始窗口
    public void createGame() {
        f.setVisible(false);
        int x = f.getX();
        int y = f.getY();
        f = new Frame("贪吃蛇游戏开始");
        sk = new Snake();        //蛇
        fd = new Food();
        this.setAtt(x, y);        //为了随时更新位置
        f.setLayout(null);
        gc = new GameCanvas();
        gc.canvasInit();            //所以
        gc.setBounds(0, 0, fWidth * 2 / 3, fHeight);    //gc宽度为300，高度为450
        gc.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        sk.setOritention(OritentionEum.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        sk.setOritention(OritentionEum.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        sk.setOritention(OritentionEum.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        sk.setOritention(OritentionEum.RIGHT);
                        break;
                }
            }
        });
        gc.setFocusable(true);                //画布获取焦点
        f.add(gc);
        ol = new OerationList().listInit();
        ol.setBounds(310, 75, fWidth / 4, fHeight * 2 / 3);
        f.add(ol);
        new updateTimeThread().start();    //更新时间线程
        new updateCanvasThread().start();//绘画线程更新
    }

    public int getWidth() {
        return f.getWidth();
    }

    public int getHeight() {
        return f.getHeight();
    }

    public int getX() {            //返回当前窗口的X坐标
        return f.getX();
    }

    public int getY() {
        return f.getY();
    }

    //绘画线程
    class updateCanvasThread extends Thread {
        public void run() {
            while (true) {
                gc.repaint();
                String sp = ol.getSpeed();
                try {
                    switch (sp) {
                        case "一级":
                            Thread.sleep(100);
                            break;
                        case "二级":
                            Thread.sleep(300);
                            break;
                        case "三级":
                            Thread.sleep(500);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //更新界面线程
    class updateTimeThread extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ol.updateTime();
            }
        }
    }

    //画布
    class GameCanvas extends Canvas {
        public GameCanvas canvasInit() {
            this.setVisible(true);
            return this;
        }

        public void update(Graphics g) {
            Image img = this.createImage(fWidth * 2 / 3, fHeight);    //不能直接创建Image对象
            Graphics gg = img.getGraphics();
            gg.setColor(Color.GRAY);    //灰色
            gg.fillRect(0, 0, fWidth * 2 / 3, fHeight);
            gg.setColor(Color.BLACK);
            gg.drawRect(10, 50, 250, 350);            //起点10，,50，宽 250 高350  70行 50列的矩阵
            if (!sk.crashed()) {
                if (sk.eatFood(fd)) {        //蛇如果吃到食物就尾部增长
                    fd.updateFood();
                    sk.growTail();
                    ol.updateScore();
                }
                sk.drawSnake(gg, rate);       //画蛇的时候传入一个rate来控制大小
                fd.drawFood(gg, rate);
            } else {            //游戏结束画面
                gg.setFont(new Font("微软雅黑", 2, 15));            //设置字体
                gg.setColor(Color.WHITE);
                gg.fillRect(60, 175, 150, 40);
                gg.setColor(Color.RED);
                gg.drawString("游戏结束  得分" + ol.getScore(), 80, 200);
            }
            g.drawImage(img, 0, 0, this);
            paint(g);
        }

        public void paint(Graphics g) {
        }
    }

}