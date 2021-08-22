import jdk.jshell.SourceCodeAnalysis;

import java.util.Scanner;

public class Fibonacci
{
    public static void main(String[] args)
    {
        long sum1=1,sum2=1,n;
        System.out.println("请输入一个整数:");
        Scanner input = new Scanner(System.in);
        n=input.nextInt();
        for (int i = 1; i < n ; i++)
        {
            sum2 = sum1 + sum2;
            sum1 = sum2 - sum1;
        }
        System.out.println("第"+n+"个Fibonacci数为:"+sum2);
    }
}