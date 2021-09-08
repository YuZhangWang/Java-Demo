package 贪吃蛇;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

//定义食物的类，显示和随机生成
public class Food {
    Location foodla = new Location(20, 20);        //食物的初始位置
    private boolean iseat = false;    //默认没有被吃

    public void drawFood(Graphics g, int rate) {
        g.setColor(Color.RED);
        g.fillRect(foodla.getY() * rate + 10, foodla.getX() * rate + 50, rate, rate);
    }

    //获取食物的坐标
    public Location getFoodLocation() {
        return foodla;
    }

    //设定食物坐标
    public void setLocation(Location x) {
        this.foodla = x;
    }

    //食物是否被吃
    public boolean isEat() {
        return iseat;
    }

    //设定食物是否被吃状态
    public void setEat(boolean b) {
        this.iseat = b;
    }

    //如果食物被吃了，就更新食物坐标,食物坐标在（90,60）的范围且不与蛇身重合
    public void updateFood() {
        //这里只是随机产生的坐标，有可能食物在蛇身子上，因为不想用遍历的方式来获取哪些坐标是空白的，有时间记得回来改
        Random am = new Random();
        foodla = new Location(am.nextInt(70), am.nextInt(50));
    }
}