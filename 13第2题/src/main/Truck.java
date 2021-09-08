package main;

public class Truck implements Transport {
    //计算卡车运输费用
    @Override
    public void CalculationCost(double weight, double mileage) {
        if (mileage > 1000 || weight > 60) {
            System.out.println(-1);
        } else {
            System.out.println("卡车运输费用为:" + (weight * mileage * 120));
        }
    }
}
