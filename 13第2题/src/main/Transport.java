package main;

public interface Transport
{
    public static final int PLANE = 1;
    public static final int TRAIN = 2;
    public static final int TRUCK = 3;
    public abstract void CalculationCost(double weight,double mileage);
}