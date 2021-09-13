package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/13 21:18
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Animal {
    // 种类
    protected String kind;
    // 名称
    protected String name;
    // 身高
    protected double height;

    // 吃
    public void eat(String food) {
        System.out.println("J");
        System.out.println("Animal eat " + food);
    }

    public Animal() {
        System.out.println("H");
    }

    public Animal(String kind, String name, double height) {
        System.out.println("G");
        this.kind = kind;
        this.name = name;
        this.height = height;
    }

    {
        System.out.println("E");
    }

    static {
        System.out.println("F");
    }
}
