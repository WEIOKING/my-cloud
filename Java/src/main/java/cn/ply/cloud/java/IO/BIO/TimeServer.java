package cn.ply.cloud.java.IO.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ply
 * @Description 阻塞式的IO通讯服务器
 * @Date created in 2022/3/23
 * @ModifiedBy
 */
public class TimeServer {
    public static final String TIME_ORDER = "TIME";
    public static final int DEFAULT_PORT = 8080;
    public static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)){
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                TimeServerHandler handler = new TimeServerHandler(socket);
                //使用线程池限制线程启动个数，提高单个线程使用率
                executorService.submit(handler);
            }
        } catch (IOException e) {
            System.out.println("The TimeServer close！");
        }
    }
}
