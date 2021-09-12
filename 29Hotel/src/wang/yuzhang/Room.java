package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/12 17:01
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Room {
    // 层数
    private int row;
    // 第几个(下标)
    private int col;
    // 入住的用户名称
    private String username;

    public Room() {
    }

    public Room(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 入住
     *
     * @param username
     */
    public void in(String username) {
        if (isEmpty()) {
            this.username = username;
            System.out.println(username + "入住成功");
        } else
            System.out.println(username + "");

    }

    /**
     * 退房
     *
     * @return 返回退房的用户名
     */
    public String out() {
        if (this.username == null) {
            System.out.println("房间本来就是空");
            return null;
        } else {
            String s = this.username;
            this.username = null;
            return s;
        }
    }

    /**
     * 是否是空房间
     *
     * @return true表示是空房间
     */
    public boolean isEmpty() {
        if (this.username == null) {
            System.out.println("房间空");
            return true;
        } else {
            System.out.println("房间不空");
            return false;

        }
    }

    /**
     * 返回房间的状态
     *
     * @return 由房间编号和用户姓名组成
     */
    public String status() {
        String status = "Room[" + row + " " + col + "]:"
                + (isEmpty() ? "空" : this.username);
        return status;
    }
}
