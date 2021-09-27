package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/26 11:36
 * @link: https://www.yuzhang.wang/
 ***********************************/

import java.util.*;

/**
 * 一副扑克牌
 *
 * @author ldl
 * @version 1.0
 * @date 2021-09-24 17:03
 */
public class Poker {
    // 属性 --> 存储了一副扑克牌
    private String[] cards;

    // 创建一个 Poker 对象后, 其中的牌 应该都已经创建好了
    public Poker() {
        Card cards = new Card();
        this.cards = cards.poker();
    }

    // 方法: 可以发牌，依次给指定的多个 Player 玩家 发牌
    public HashMap<String, String[]> send(Player p1name, Player p2name, Player p3name) {
        HashMap<String, String[]> afterSend = new HashMap<>();
        Poker poker = new Poker();
        String[] p1 = new String[0];
        String[] p2 = new String[0];
        String[] p3 = new String[0];
        String[] land = new String[3];
        for (int i = 0; i < poker.cards.length - 3; i++) {
            if (i % 3 == 0) {
                p1 = Arrays.copyOf(p1, p1.length + 1);
                p1[p1.length - 1] = poker.cards[i];
            } else if (i % 3 == 1) {
                p2 = Arrays.copyOf(p2, p2.length + 1);
                p2[p2.length - 1] = poker.cards[i];
            } else if (i % 3 == 2) {
                p3 = Arrays.copyOf(p3, p3.length + 1);
                p3[p3.length - 1] = poker.cards[i];
            }
        }

        System.arraycopy(poker.cards, 50, land, 0, 3);

        afterSend.put(p1name.getName(), p1);
        afterSend.put(p2name.getName(), p2);
        afterSend.put(p3name.getName(), p3);
        String landlord = "land";
        afterSend.put(landlord, land);
        System.out.println("发牌完成");
        return afterSend;
    }

    // 方法: 可以洗牌
    public void wash() {
        Collections.shuffle(Arrays.asList(cards));
    }

    private List<String> ArrayList(List<String> asList) {

        return asList;
    }

    // 方法: 接收 玩家 打出的 扑克牌
    ArrayList out = new ArrayList();

    public ArrayList out(String outPoker) {
        out.add(outPoker);
        return out;
    }
}
