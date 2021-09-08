package 贪吃蛇;

import 贪吃蛇.Snake.OritentionEum;

public class Location {
    //坐标类，不光记录坐标位置，必要的时候还记录方向信息
    private int x;
    private int y;
    private OritentionEum oritention;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location(int x, int y, OritentionEum oritention) {
        this(x, y);
        this.oritention = oritention;
    }

    public Location(Location la, OritentionEum oritention) {
        this(la.getX(), la.getY());
        this.oritention = oritention;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void recordOritention(OritentionEum oritention) {
        this.oritention = oritention;
    }

    public OritentionEum getRecordOritention() {
        return this.oritention;
    }
}
