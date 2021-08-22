package main;

public class DivisionByZero
{
    public static void division()
    {
        int a = 10;
        int b = 0;
        try
        {
            System.out.println(a + "/" + b + "=" + (a/b));
        }
        catch (ArithmeticException e)
        {
            System.out.println("出现算数异常");
        }
        finally
        {
            System.out.println("处理善后清理工作");
        }
        System.out.println("后续代码");
    }

    public static void main(String[] args)
    {
        DivisionByZero d =new DivisionByZero();
        d.division();
        System.out.println("被除数不能为0");
    }
}
