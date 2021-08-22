package main;

public class Squ extends Rect
{
    //构造方法
    Squ(double bianchang)
    {
        super(bianchang,bianchang);
    }
    //输出正方形长等于宽
    @Override
    public void cha()
    {
        System.out.println("长等于宽");
    }
}
