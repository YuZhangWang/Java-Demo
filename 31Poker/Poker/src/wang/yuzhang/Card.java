package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/26 11:39
 * @link: https://www.yuzhang.wang/
 ***********************************/

/**
 * 一张牌
 *
 * @author ldl
 * @version 1.0
 * @date 2021-09-24 17:00
 */
public class Card {
    // 属性
    // 花色: 红桃♥、黑桃♠、方块、梅花♣、王
    // 点数: 1(A)~9 10 J Q K 小 大
    private String type[] = {"♦", "♣", "♠", "♥"};
    private String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private String[] kings = {"大王", "小王"};
    String[] pokers = new String[54];

    public Card(String s) {
    }

    public Card() {

    }

    //生成一副牌
    public String[] poker() {
        int a = 0;

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < type.length; j++) {
                pokers[a++] = type[j] + numbers[i];
            }
        }
        System.arraycopy(kings, 0, pokers, 51, 2);
        return pokers;
    }
    // toString 输出的格式: 红桃A 或 ♥A

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String[] getNumbers() {
        return numbers;
    }

    public void setNumbers(String[] numbers) {
        this.numbers = numbers;
    }

    public String[] getKings() {
        return kings;
    }

    public void setKings(String[] kings) {
        this.kings = kings;
    }

    public String[] getCard() {
        return pokers;
    }

    public void setCard(String[] pokers) {
        this.pokers = pokers;
    }
}
