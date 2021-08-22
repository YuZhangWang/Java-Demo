public class Fish extends Animal
{
    public Fish(String name)
    {
        super(name,0);
    }

    @Override
    public void move()
    {
        System.out.println(getname()+" which has "+getLegs()+" legs is "+"swimming.");
    }
}
