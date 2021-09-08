package main;

public class HourlyEmployee extends Employee {
    private double hourSalary;
    private double monthHour;

    void setHourSalary(double hourSalary) {
        this.hourSalary = hourSalary;
    }

    void setMonthHour(double monthHour) {
        this.monthHour = monthHour;
    }

    @Override
    double getSalary(int month) {
        double salary;
        if (monthHour > 160) {
            salary = (hourSalary * 160 + hourSalary * (monthHour - 160) * 1.5);
        } else {
            salary = (hourSalary * monthHour);
        }
        if (getEmployeeBirthdayMonth() == month) {
            return (salary + 100);
        } else {
            return salary;
        }
    }
}