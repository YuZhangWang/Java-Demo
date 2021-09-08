package main;

public class test {
    public static void main(String[] args) {
        //测试圆
        Circle circle = new Circle(3);
        circle.zhouchang();
        circle.area();
        //测试矩形
        Rect rect = new Rect(10, 5);
        rect.zhouchang();
        rect.area();
        rect.cha();
        //测试正方形
        Squ squ = new Squ(5);
        squ.zhouchang();
        squ.area();
        squ.cha();
    }
}
