package wang.yuzhang;

import java.util.Scanner;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/8 21:29
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Test06_9_8 {
    public static void main(String[] args) {
        // 酒店住房管理系统
        System.out.println("欢迎使用 Xxx 酒店住房管理系统");

        // 菜单: 入住, 退房, 显示房间状态, 退出系统
        showMenu();
    }

    private static void showMenu() {
        while (true) {
            System.out.println("菜单： 1：入住\n\t  2：退房\n\t  3：显示房间状态\n\t  4：退出系统\n");

            int i = new Scanner(System.in).nextInt();
            if (i == 4) {
                break;
            } else if (i == 1) {
                Check_In();
            } else if (i == 2) {
                Check_out();
            } else if (i == 3) {
                showStatus();
            }
        }
    }

    /**
     * 初始, 酒店共4层，每层6个单人间
     */
    public static String[][] rooms = new String[4][6];

    // 入住(已有人住的房间不能再住人)
    public static void Check_In() {
        System.out.println("酒店共4层，每层6个单人间，请输入楼层号（1-4）：");
        int floor_Num = new Scanner(System.in).nextInt() - 1;
        while (floor_Num < 0 || floor_Num > 3) {
            System.out.println("输入楼层号有误！请重新输入：");
            floor_Num = new Scanner(System.in).nextInt() - 1;
        }
        System.out.println("请输入房间号（1-6）：");
        int room = new Scanner(System.in).nextInt() - 1;
        while (room < 0 || room > 5) {
            System.out.println("输入房间号有误！请重新输入：");
            room = new Scanner(System.in).nextInt() - 1;
        }

        if (rooms[floor_Num][room] == null) {
            rooms[floor_Num][room] = "已入住";
            System.out.println("入住成功");
        } else {
            System.out.println("重新输入，房间已经被占用");
        }
    }

    // 退房(没有人住的房间不需要退房)
    public static void Check_out() {
        System.out.println("请输入退房楼层号：");
        int floor_Num = new Scanner(System.in).nextInt() - 1;
        while (floor_Num < 0 || floor_Num > 3) {
            System.out.println("输入楼层号有误！请重新输入：");
            floor_Num = new Scanner(System.in).nextInt() - 1;
        }
        System.out.println("请输入退房房间号：");
        int room_Num = new Scanner(System.in).nextInt() - 1;
        while (room_Num < 0 || room_Num > 5) {
            System.out.println("输入房间号有误！请重新输入：");
            room_Num = new Scanner(System.in).nextInt() - 1;
        }

        if (rooms[floor_Num][room_Num] != null) {
            rooms[floor_Num][room_Num] = null;
            System.out.println("退房成功");
        } else {
            System.out.println("房间为空，请重新选择退房");
        }
    }

    // 房间状态
    public static void showStatus() {
        System.out.println("======================房间状态=====================");
        for (int i = 0; i < rooms.length; i++) {
            System.out.print("房号:\t");
            for (int j = 0; j < rooms[i].length; j++) {
                System.out.print(i + "" + j + "\t\t");
            }
            System.out.println();
            System.out.print("状态:\t");
            for (int j = 0; j < rooms[i].length; j++) {
                String status = rooms[i][j];
                status = status == null ? "空" : status;
                System.out.print(status + "\t\t");
            }
            System.out.println();
        }
        System.out.println("==================================================");
    }
}