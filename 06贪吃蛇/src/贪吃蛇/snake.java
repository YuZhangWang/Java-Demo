package 贪吃蛇;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
    //private  int[][] snakeMap = new int[45][30];      45行30列
    //将蛇身的坐标加入一个队列，从蛇尾巴开始加
    public LinkedList<Location> snake = new LinkedList<>();
    //方向数组，用来确定蛇尾巴的方向  ,每次Frame监听到有效的方向按键，则记录位置并记录方向
    public LinkedList<Location> oritention = new LinkedList<>();
    private boolean hasCrawl = true;
    //初始化数组，
    {
        snake.add(new Location(2,3));        //First remove
        snake.add(new Location(2,4));
        snake.add(new Location(2,5));
        snake.add(new Location(2,6));
        snake.add(new Location(2,7));        //Last 蛇头
    }
    private OritentionEum oe = OritentionEum.RIGHT;    //方向默认开始向右
    private Location snakeHead  = snake.getLast();
    private boolean isEat = false;                        //是否吃到
    //获取蛇身,将蛇尾是否增长计入里面
    public LinkedList<Location> getSnake(){
        this.growTail();
        return snake;
    }
    //获取蛇尾位置
    public Location getSnakeTail() {
        return snake.getFirst();
    }
    //获取蛇头位置
    public    Location getSnakeHead() {
        return snake.getLast();
    }
    //设置蛇头
    public void setSnakeHead(Location loc) {
        this.snakeHead = loc;
    }
    //蛇运动, Location的数组行索引其实对应的是画布的Y坐标
    public void snakeCrawl() {
        OritentionEum oe = this.getHeadOritention();
        Location snakeHead = this.getSnakeHead();
        switch(oe) {
            case DOWN : snake.addLast(new Location(snakeHead.getX()+1,snakeHead.getY()));break;
            case UP : snake.addLast(new Location(snakeHead.getX()-1,snakeHead.getY()));break;
            case RIGHT : snake.addLast(new Location(snakeHead.getX(),snakeHead.getY()+1));break;
            case LEFT : snake.addLast(new Location(snakeHead.getX(),snakeHead.getY()-1));break;
        }
        snake.remove();
        this.hasCrawl = true;            //蛇走完一部才设置为true，这样才允许方向的设置
        if(!oritention.isEmpty()) {
            Location tail  = this.getSnakeTail();
            Location la  = oritention.getFirst();        //蛇头转向的标记点
            if(tail.getX() == la.getX() && tail.getY()==la.getY()) {    //蛇尾走到拐点
                oritention.remove();
            }
        }
    }
    //画蛇
    public void drawSnake(Graphics g,int rate) {
        g.setColor(Color.YELLOW);
        for(Location i: snake) {
            //g.fillRect(i.getX()*rate, i.getY()*rate, rate, rate);
            g.fillRect(i.getY()*rate+10,i.getX()*rate+50, rate, rate);
        }
        this.snakeCrawl();
    }
    //设置方向，方向为右的时候只能设置方向为上下，为上下的时候只能设置方向为左右
    //设置方向的时候同时将将方向加入方向队列用来对蛇尾方向进行标记
    //存在一种情况，方向已经设置但是由于线程更新间隔大于按键监听间隔，
    //所以可能存在当蛇在横着走的时候可能先设置上或者下成功，但这时候绘画线程却没有更新（），在这极短的时间内又重新设置了左或者右
    //这时候就会发现蛇可能在水平方向来回运动的情况
    //设置一个标识变量 flag 设置方向的时候将其设置为false，蛇crawl之后设置为true
    public void setOritention(OritentionEum oe) {
        if(this.hasCrawl) {
            OritentionEum currentOe = this.getHeadOritention();                //获取当前蛇的方向
            if(currentOe == OritentionEum.RIGHT || currentOe == OritentionEum.LEFT) {
                if(oe==OritentionEum.UP || oe==OritentionEum.DOWN)
                {oritention.add(new Location(this.getSnakeHead(),this.getHeadOritention()));
                    this.oe = oe; this.hasCrawl=false;}
            } else{
                if(oe==OritentionEum.RIGHT || oe==OritentionEum.LEFT)
                {oritention.add(new Location(this.getSnakeHead(),this.getHeadOritention()));
                    this.oe=oe;this.hasCrawl=false;}
            }
        }
    }
    //获取当前蛇头的方向
    public OritentionEum getHeadOritention() {
        return oe;
    }
    //获取蛇尾方向,方向队列不为空，就按照队列First方向，否则就按照蛇头方向
    public OritentionEum getTailOritention() {
        if(!oritention.isEmpty()) {
            Location la  = oritention.getFirst();        //蛇头转向的标记点
            //Location tail = this.getSnakeTail();
            return la.getRecordOritention();
        }
        else return this.getHeadOritention();
    }
    //蛇吃食物,同时在蛇尾增加长度（需要根据方向来确定增加在哪里）
    public boolean eatFood(Food d) {
        Location fla=d.getFoodLocation();    //食物坐标
        Location sla = this.getSnakeHead();    //蛇头坐标
        if(sla.getX()==fla.getX() && fla.getY() == sla.getY()) {
            isEat = true;
            d.setEat(true);         //更新食物里面是否被吃的状态
            return true;
        }
        return false;
    }
    //在蛇尾增加一个单位的长度
    //如果方向队列为空，则按照当前蛇前进的方向来反向增加
    //否则就按照蛇尾方向队列oritention的First元素来反向增加蛇尾长度
    public void growTail() {
        if(this.isEat) {
            OritentionEum oe = this.getTailOritention();
            //System.out.println(oe);
            Location la = this.getSnakeTail();
            switch(oe) {
                case UP: snake.addFirst(new Location(la.getX()+1,la.getY()));break;
                case DOWN: snake.addFirst(new Location(la.getX()-1,la.getY()));break;
                case LEFT: snake.addFirst(new Location(la.getX(),la.getY()+1));break;
                case RIGHT: snake.addFirst(new Location(la.getX(),la.getY()-1));break;
            }
            //增长完后复位
            this.isEat = false;
        }
    }
    // 碰撞游戏结束
    public boolean crashed() {
        Location la = this.getSnakeHead();
        int x = la.getX(),y=la.getY();
        //只要蛇头没撞上就OK
        //分为撞墙和撞蛇身
        if(x>=0 && x<=69 && y>=0 && y<=49) {        //在画布范围里
            boolean flag = false;
            for(int i=0;i<snake.size()-1;i++) {
                Location sla = snake.get(i);
                if(sla.getX()==x && sla.getY()==y) //蛇与自身碰撞了
                    flag = true;
            }
            return flag;
        } else return true;
    }
    //蛇运行方向枚举类
    enum OritentionEum{
        //4个枚举方向的枚举实例
        UP,DOWN,LEFT,RIGHT;
    }
    public static void main(String [] args) {
        Snake sk = new Snake();
        for(Location i : sk.snake) {
            System.out.println(i.getX()+" "+i.getY());
        }
        System.out.println(sk.snake.getLast().getY());
    }
}