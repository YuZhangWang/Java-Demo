public class Person
{
    String name;
    int age;
    //true代表男性，false代表女性
    boolean gender;
    //true代表已婚，false代表未婚
    boolean marriage;

    public  void marry(Person p,Person partner)
    {
        if((partner.age>=24 && p.age>=22 && !partner.marriage && !p.marriage)||(partner.age>=22 && p.age>=24 && !partner.marriage && !p.marriage))
        {
            if(partner.gender)
            {
                System.out.println("恭喜" + partner.name+"先生和"+p.name+"小姐喜结连理");
            }
            if(p.gender)
            {
                System.out.println("恭喜" + p.name+"先生和"+partner.name+"小姐喜结连理");
            }
            partner.marriage=true;
            p.marriage=true;
        }
        else if((partner.age<24 && partner.gender) || (p.age<22 && !p.gender) || (partner.age<22 && !partner.gender) || (p.age<24 && p.gender))
        {
            if (partner.age < 24 && partner.gender)
            {
                System.out.println(partner.name + "先生未到法定结婚年龄");
            }
            if (p.age < 22 && !p.gender)
            {
                System.out.println(p.name + "小姐未到法定结婚年龄");
            }
            if (p.age<24 && p.gender)
            {
                System.out.println(p.name + "先生未到法定结婚年龄");
            }
            if(partner.age<22 && !partner.gender)
            {
                System.out.println(partner.name + "小姐未到法定结婚年龄");
            }
        }
        else if(partner.marriage || p.marriage)
        {
                System.out.println(partner.name+p.name+"中某一方已结婚");
        }
    }

    public void setPerson(Person p, String name, int age, boolean gender)
    {
        p.name=name;
        p.age=age;
        p.gender=gender;
    }
}
