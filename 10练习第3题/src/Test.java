public class Test
{
    public static void main(String[] args)
    {
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        Person person4 = new Person();
        Person person5 = new Person();
        //张三，25岁，男
        person1.setPerson(person1,"张三",25,true);
        //王丽，25岁，女
        person2.setPerson(person2,"王丽",25,false);
        //李四，20岁，男
        person3.setPerson(person3,"李四",20,true);
        //赵莉，26岁，女
        person4.setPerson(person4,"赵莉",26,false);
        //王五，28岁，男
        person5.setPerson(person5,"王五",28,true);
        person1.marry(person1,person2);
        person3.marry(person3,person4);
        person5.marry(person5,person2);
    }
}
