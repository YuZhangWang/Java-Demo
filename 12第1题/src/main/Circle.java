package main;

public class Circle extends Shape {
    static double PI = 3.14;
    double banjing;

    //构造方法
    Circle(double banjing) {
        this.banjing = banjing;
    }

    //计算圆周长
    @Override
    public void zhouchang() {
        System.out.println("圆的周长为:" + (2 * banjing * PI));
    }

    //计算圆面积
    @Override
    public void area() {
        System.out.println("圆的面积为:" + (banjing * banjing * PI));
    }
}
