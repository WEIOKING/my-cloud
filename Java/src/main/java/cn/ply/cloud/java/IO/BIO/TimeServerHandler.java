package cn.ply.cloud.java.IO.BIO;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/23
 * @ModifiedBy
 */
public class TimeServerHandler implements Runnable{
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream())){
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println("ORDER :" + line);
                String outLine = TimeServer.TIME_ORDER.equalsIgnoreCase(line) ? new Date().toString() : "BAD ORDER";
                Thread.sleep(1000);
                writer.println(outLine);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
