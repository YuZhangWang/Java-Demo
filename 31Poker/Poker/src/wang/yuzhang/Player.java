package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/26 11:38
 * @link: https://www.yuzhang.wang/
 ***********************************/

/**
 * @author ldl
 * @version 1.0
 * @date 2021-09-24 17:06
 */
public class Player {
    public Object out;
    // 有属性, 多个手牌
    // 方法: 可以出牌, 选择指定牌, 出牌

    private String name;//姓名

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
