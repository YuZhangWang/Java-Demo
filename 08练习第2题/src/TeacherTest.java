public class TeacherTest
{
    public static void main(String[] args)
    {
        Teacher teacher= new Teacher();
        teacher.name="张三";
        teacher.age=25;
        teacher.salary=4000;
        System.out.println(teacher.age+"岁"+"教师"+teacher.name+"的薪水为"+teacher.salary);
        System.out.println("加薪之后变化为:");
        teacher.Payrise();
        System.out.println(teacher.age+"岁"+"教师"+teacher.name+"的薪水为"+teacher.salary);
    }
}
