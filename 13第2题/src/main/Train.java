package main;

public class Train implements Transport {
    //计算火车运输费用
    @Override
    public void CalculationCost(double weight, double mileage) {
        if (mileage > 900) {
            System.out.println("火车运输费用为:" + (weight * mileage * 300));
        } else {
            System.out.println("火车运输费用为:" + (weight * mileage * 250));
        }
    }
}
