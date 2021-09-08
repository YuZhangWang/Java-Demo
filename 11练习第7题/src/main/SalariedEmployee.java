package main;

public class SalariedEmployee extends Employee {
    private double monthSalary;

    void setMonthSalary(int monthSalary) {
        this.monthSalary = monthSalary;
    }

    @Override
    double getSalary(int month) {
        if (getEmployeeBirthdayMonth() == month) {
            return (monthSalary + 100);
        } else {
            return monthSalary;
        }
    }
}