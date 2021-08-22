package main;

public class Aircraft implements Transport
{
    //计算飞机运输费用
    @Override
    public void CalculationCost(double weight, double mileage)
    {
        if (mileage <= 500)
        {
            System.out.println(-1);
        }
        else
        {
            System.out.println("飞机运输费用为:"+(weight*mileage*750));
        }
    }
}
