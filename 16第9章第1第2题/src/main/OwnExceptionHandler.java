package main;

public class OwnExceptionHandler
{
    public static void main(String[] args)
    {
        OwnExceptionSource es =new OwnExceptionSource();
        try
        {
            es.a();
        }
        catch (Exception e)
        {
            System.out.println("出现异常");
        }
        finally
        {
            System.out.println("处理善后清理工作");
        }
        System.out.println("后续代码");
    }
}
