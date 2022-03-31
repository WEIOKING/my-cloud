package cn.ply.cloud.java.IO.NIO;


/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/24
 * @ModifiedBy
 */
public class TimeServer {
    public static final String TIME_ORDER = "TIME";
    public static final int DEFAULT_PORT = 8080;
    public static void main(String[] args) {
        new Thread(new MultiTimeServer(DEFAULT_PORT)).start();
    }
}
