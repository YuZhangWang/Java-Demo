public class Zoo {
    public static void main(String[] args) {
        Animal animal1 = new Animal("Rats", 4);
        Animal animal2 = new Animal("Fishes", 0);
        Animal animal3 = new Animal("Birds", 2);
        Fish fish1 = new Fish("Catfish");
        Fish fish2 = new Fish("Weever");
        Fish fish3 = new Fish("Salmon");
        Bird bird1 = new Bird("Eagle");
        Bird bird2 = new Bird("Penguin");
        Bird bird3 = new Bird("Parrot");
        animal1.move();
        animal2.move();
        animal3.move(3);
        fish1.move();
        fish2.move();
        fish3.move();
        bird1.move();
        bird2.move();
        bird3.move();
    }
}
