package main;

public class test {
    public static void main(String[] args) {
        Employee[] employee = new Employee[4];
        SalariedEmployee employee1 = new SalariedEmployee();
        HourlyEmployee employee2 = new HourlyEmployee();
        SalesEmployee employee3 = new SalesEmployee();
        BasedPlusSalesEmployee employee4 = new BasedPlusSalesEmployee();

        //员工张三，一月生日,固定工资员工,月薪5000元
        employee1.setEmployeeName("张三");
        employee1.setEmployeeBirthdayMonth(1);
        employee1.setMonthSalary(5000);
        employee[0] = employee1;
        //员工李四，二月生日，时薪工资员工，时薪18元，工作时长170小时
        employee2.setEmployeeName("李四");
        employee2.setEmployeeBirthdayMonth(2);
        employee2.setHourSalary(18);
        employee2.setMonthHour(170);
        employee[1] = employee2;
        //员工王五，三月生日，销售员工，销售额10000件，提成率0.05
        employee3.setEmployeeName("王五");
        employee3.setEmployeeBirthdayMonth(3);
        employee3.setMonthlySales(10000);
        employee3.setRoyaltyRate(0.05);
        employee[2] = employee3;
        //员工赵六，四月生日，有固定底薪的销售人员，销售额8000件，提成率0.06，底薪3000
        employee4.setEmployeeName("赵六");
        employee4.setEmployeeBirthdayMonth(4);
        employee4.setBasicSalary(3000);
        employee4.setMonthlySales(8000);
        employee4.setRoyaltyRate(0.06);
        employee[3] = employee4;

        System.out.println("员工" + employee[0].getemployeeName() + "一月工资为" + employee[0].getSalary(1));
        System.out.println("员工" + employee[1].getemployeeName() + "一月工资为" + employee[1].getSalary(1));
        System.out.println("员工" + employee[2].getemployeeName() + "一月工资为" + employee[2].getSalary(1));
        System.out.println("员工" + employee[3].getemployeeName() + "一月工资为" + employee[3].getSalary(1));
    }
}