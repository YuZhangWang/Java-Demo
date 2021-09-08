package 贪吃蛇;

import java.awt.Button;
import java.awt.GridLayout;
//panel是容器，默认为FlowLayout布局，不能独立存在
//该Panel主要容纳三个button，分别是  开始游戏  帮助  关于
//Panel最后会被加入Frame
//使用了布局管理，就不能使用绝对定位了。
import java.awt.Panel;

@SuppressWarnings("serial")
public class StartPanel extends Panel {
    private Button startGame;
    private Button help;
    private Button info;
    private GameHelp gh;    //点击帮助按钮弹出的Frame
    private Info ifo;        //关于的窗口
    private GameFrame gf;    //与主窗口关联，游戏开始按钮 的时间监听 执行的动作 与其相关

    public StartPanel(GameFrame game) {
        super();
        gf = game;
    }

    public StartPanel panelInit() {
        startGame = new Button("开始游戏");
        help = new Button("帮助");
        //因为 startPanel已经设置了布局管理，所以setSize，setLocation，setBounds就失效了。
        //给startGame 按钮增加监听,直接用lambda ，ActionListener是个函数式接口
        startGame.addActionListener(e -> {
            gf.createGame();
        });
        //给“帮助”按钮加上点击监听
        help.addActionListener(e -> {
            if (gh == null) {
                gh = new GameHelp();
                gh.helpInit(gf.getX(), gf.getY(), gf.getHeight());
            } else
                gh.helpInit(gf.getX(), gf.getY(), gf.getHeight());
        });
        info = new Button("关于");
        //给关于按钮添加监听
        info.addActionListener(e -> {
            if (ifo == null) {
                ifo = new Info();
                ifo.infoInit(gf.getX(), gf.getY(), gf.getHeight());
            } else
                ifo.infoInit(gf.getX(), gf.getY(), gf.getHeight());
        });
        this.setLayout(new GridLayout(3, 1, 25, 25));   //网格布局，三行一列
        this.add(startGame);
        this.add(help);
        this.add(info);

        this.setVisible(true);
        return this;
    }
}