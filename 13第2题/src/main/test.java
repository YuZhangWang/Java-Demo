package main;

public class test {
    public static void main(String[] args) {
        //测试卡车运输费用
        Truck truck = new Truck();
        truck.CalculationCost(60, 1001);
        truck.CalculationCost(61, 1000);
        truck.CalculationCost(60, 1000);
        //测试火车运输费用
        Train train = new Train();
        train.CalculationCost(10, 901);
        train.CalculationCost(10, 900);
        //测试飞机运输费用
        Aircraft aircraft = new Aircraft();
        aircraft.CalculationCost(10, 500);
        aircraft.CalculationCost(10, 501);
    }
}
