package wang.yuzhang;

/***********************************
 * @author: YuZhangWang
 * @date: 2021/9/12 17:02
 * @link: https://www.yuzhang.wang/
 ***********************************/
public class Runner {
    public static void main(String[] args) {
        // TODO: 创建酒店
        Hotel hotel = null;
        // 创建系统
        RoomManageSystem rms = new RoomManageSystem(hotel);
        rms.start();

    }
}
