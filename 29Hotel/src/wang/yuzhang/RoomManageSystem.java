package wang.yuzhang;

import java.util.Scanner;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/12 17:02
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class RoomManageSystem {
    // 被系统管理的酒店
    private Hotel hotel;

    private boolean running;

    public RoomManageSystem(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("系统正在启动中...");
        running = true;
        System.out.println("系统启动成功");

        while (this.isRunning()) {
            this.showMenu();
            System.out.println("请选择对应的菜单:");
            int menuNo = sc.nextInt();
            this.doMenu(menuNo);
        }

        System.out.println("系统正在关闭...");
        System.out.println("系统已关闭, 欢迎再次使用本系统");
    }

    public void close() {
        running = false;
    }

    public void showMenu() {
        // 显示菜单
        System.out.println("===========菜单============");
        System.out.println("1.入住");
        System.out.println("2.退房");
        System.out.println("3.房间状态");
        System.out.println("4.退出系统");

    }

    public void doMenu(int menuNo) {
        // 根据用户选择的菜单执行对应的操作
        Room[][] rooms = hotel.getRooms();
        if (menuNo == 1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入楼层");
            int row = sc.nextInt();
            System.out.println("请输入房间号");
            int col = sc.nextInt();
            Room room1 = new Room(row, col);
            if (!room1.isEmpty())
                System.out.println("房间有人");
            else {
                System.out.println("输入名字");
                String name = sc.next();
                room1.in(name);
                System.out.println("入住成功");
                rooms[row][col] = room1;
            }
        }
        if (menuNo == 2) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入楼层");
            int row = sc.nextInt();
            System.out.println("请输入房间号");
            int col = sc.nextInt();
            Room room = new Room(row, col);
            if (room.isEmpty())
                System.out.println("房间已经空");
            else {
                room.out();
                rooms[row][col] = room;
            }

        }
        if (menuNo == 3) {
            for (int i = 0; i < hotel.getRooms().length; i++) {
                for (int j = 0; j < hotel.getRooms()[i].length; j++) {

                    String status = rooms[i][j].status();
                    System.out.println(status);
                }
            }
        }
        if (menuNo == 4) {
            close();
        } else
            System.out.println("暂无此功能!");

    }
}
