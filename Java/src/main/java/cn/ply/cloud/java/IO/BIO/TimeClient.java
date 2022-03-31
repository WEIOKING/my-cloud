package cn.ply.cloud.java.IO.BIO;

import java.io.*;
import java.net.Socket;
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
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try (Socket socket = new Socket("127.0.0.1", TimeServer.DEFAULT_PORT);
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    writer.println(TimeServer.TIME_ORDER);
                    String readLine = reader.readLine();
                    System.out.println(readLine);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
