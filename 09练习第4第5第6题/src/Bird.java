public class Bird extends Animal {
    public Bird(String name) {
        super(name, 2);
    }

    @Override
    public void move() {
        System.out.println(getname() + " which has " + getLegs() + " legs is " + "flying.");
    }
}
