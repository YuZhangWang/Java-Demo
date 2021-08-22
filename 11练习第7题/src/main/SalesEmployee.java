package main;
public class SalesEmployee extends Employee
{
    private double monthlySales;
    private double royaltyRate;
    void setMonthlySales(double monthlySales)
    {
        this.monthlySales=monthlySales;
    }

    void setRoyaltyRate(double royaltyRate)
    {
        this.royaltyRate=royaltyRate;
    }

   double getMonthSales()
    {
        return monthlySales;
    }

    double getRoyaltyRate()
    {
        return royaltyRate;
    }

    @Override
    double getSalary(int month)
    {
        double salary;
        salary = (monthlySales*royaltyRate);
        if(getEmployeeBirthdayMonth() == month)
        {
            return (salary+100);
        }
        else
        {
            return salary;
        }
    }
}