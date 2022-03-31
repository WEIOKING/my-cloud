package cn.ply.cloud.java.IO.NIO;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/23
 * @ModifiedBy
 */
public class TimeClient {

    public static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            for (int i = 0; i < 10; i++) {
                executorService.submit(new TimeClientHandle("127.0.0.1", TimeServer.DEFAULT_PORT, selector));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
