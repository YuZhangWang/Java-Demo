package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/26 11:38
 * @link: https://www.yuzhang.wang/
 ***********************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Games {
    public static void main(String[] args) {
        menu();

    }

    public static void menu() {
        System.out.println("开始游戏");
        Scanner sc = new Scanner(System.in);
        Poker p = new Poker();
        HashMap<String, String[]> cards = null;
        System.out.println("请输入玩家一的名字:");
        String name1 = sc.next();
        Player p1name = new Player(name1);

        System.out.println("请输入玩家二的名字:");
        String name2 = sc.next();
        Player p2name = new Player(name2);

        System.out.println("请输入玩家三的名字:");
        String name3 = sc.next();
        Player p3name = new Player(name3);
        p.wash();
        cards = p.send(p1name, p2name, p3name);
        ArrayList out = new ArrayList();
        out.add(p.out);
    }
}
