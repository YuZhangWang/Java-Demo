package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/12 17:00
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Hotel {
    // 酒店名称
    private String name;
    // 多层，每层多少个房间
    private Room[][] rooms;

    public Hotel() {
    }

    public Hotel(String name, int row, int col) {
        this.name = name;
        Room[][] rooms = new Room[row][col];
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                rooms[i][j] = new Room(i, j);
            }
        }
        this.rooms = rooms;
    }

    // TODO: 扩展功能

    /**
     * TODO: 增多房间，row与col 不能比原来的小，增加房间的操作不能影响已入住的用户
     *
     * @param row
     * @param col
     */
    public void expandRooms(int row, int col) {
        Room[][] expendrooms = new Room[row][col];
        for (int i = 0; i < this.rooms.length; i++) {
            for (int j = 0; j < this.rooms[i].length; j++) {
                expendrooms[i][j] = this.rooms[i][j];
            }
        }
        for (int i = this.rooms.length; i < row; i++) {
            for (int j = this.rooms[i].length; j < col; j++)
                expendrooms[i][j] = new Room(i, j);
        }
        this.rooms = expendrooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }
}
