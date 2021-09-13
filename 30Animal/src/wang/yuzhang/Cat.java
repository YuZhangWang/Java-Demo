package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/13 21:19
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Cat extends Animal {
    // 继承 Animal
    static {
        System.out.println("A");
    }

    {
        System.out.println("B");
    }

    static protected String kind = "猫";

    // 走猫步
    public void catStep() {
        System.out.println("猫步,轻轻地");
    }

    public void eat(String food) {
        System.out.println("D");
        // TODO: 调用 父类的 eat 方法
        super.eat(food);
    }

    public Cat(String name, double height) {
        // TODO: 调用父类的有参构造
        super(kind, name, height);
        // TODO: 修改父类的 kind 属性值为 Cat kind 属性的值
        super.kind = "Cat kind";
        System.out.println("C");
    }

    public static void main(String[] args) {
        // 根据上述要求完善代码
        // 根据完善后的代码，创建一个 猫对象
        // 使用 猫对象 调用 eat 方法 传入 "鱼"
        // 在此处写出 ABC等的执行顺序 FAEGBCDJ
        // 例如: CBAD
        Cat cat = new Cat("caelan", 20);
        cat.eat("鱼");
    }
}
