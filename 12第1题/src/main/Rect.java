package main;

public class Rect extends Shape {
    double chang;
    double kuan;

    //构造方法
    Rect(double chang, double kuan) {
        this.chang = chang;
        this.kuan = kuan;
    }

    //计算矩形周长
    @Override
    public void zhouchang() {
        System.out.println("矩形的周长为:" + (2 * chang + 2 * kuan));
    }

    //计算矩形面积
    @Override
    public void area() {
        System.out.println("矩形的面积为:" + (chang * kuan));
    }

    //判断矩形长宽哪个长
    public void cha() {
        if (chang > kuan)
            System.out.println("长比宽大");
        if (chang < kuan)
            System.out.println("宽比长大");
    }
}
