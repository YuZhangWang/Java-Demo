import java.util.Random;

public class Prime
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        for(int i=0;i<999;i++)
        {
            int n =rand.nextInt(1000);
            boolean isPrime=true;

            if(n==1||n==0)
            {
                isPrime=false;
            }
            for(int j=2;j<Math.sqrt(n);j++)
            {
                if(n % j == 0)
                {
                    isPrime=false;
                    break;
                }
            }
            if(isPrime)
            {
                System.out.print(n+"是素数"+" ");
            }
            else
            {
                System.out.print(n+"不是素数"+" ");
            }
        }
    }
}
