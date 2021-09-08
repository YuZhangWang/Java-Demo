package main;

public class BasedPlusSalesEmployee extends SalesEmployee {
    private double basicSalary;

    void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    @Override
    double getSalary(int month) {
        double salary;
        salary = (basicSalary + (getMonthSales() * getRoyaltyRate()));
        if (getEmployeeBirthdayMonth() == month) {
            return (salary + 100);
        } else {
            return salary;
        }
    }
}