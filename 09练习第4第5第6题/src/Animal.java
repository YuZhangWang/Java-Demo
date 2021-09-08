public class Animal {
    private String name;
    private int legs;

    public Animal() {
        this.name = "AAA";
        this.legs = 4;
    }

    public Animal(String n, int l) {
        this.name = n;
        this.legs = l;
    }

    public void move() {
        System.out.println(name + " which have " + legs + " legs are " + "moving.");
    }

    public void move(int n) {
        while (n > 0) {
            System.out.println(name + " which have " + legs + " legs are " + "moving.");
            n = n - 1;
        }
    }

    String getname() {
        return name;
    }

    int getLegs() {
        return legs;
    }
}
