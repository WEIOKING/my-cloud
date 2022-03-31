package cn.ply.cloud.java.IO.NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/26
 * @ModifiedBy
 */
public class MultiTimeServer implements Runnable {
    private Selector selector;
    private boolean stop;

    public MultiTimeServer(int port) {
        try {
            //打开ServerSocketChannel，打开多路复用器
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //绑定监听端口，设置最大连接数
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            //设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //注册至多路复用器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("时间服务器已启动，监听端口" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(selectionKey);
                    } catch (IOException e) {
                        selectionKey.cancel();
                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册的资源都会自动关闭
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            //处理新接入请求
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel accept = serverSocketChannel.accept();
                accept.configureBlocking(false);
                accept.register(selector, SelectionKey.OP_READ);
            }
            //读取数据
            if (selectionKey.isReadable()) {
                SocketChannel channel = (SocketChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = channel.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String order = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("ORDER :" + order);
                    String outLine = TimeServer.TIME_ORDER.equalsIgnoreCase(order) ? new Date().toString() : "BAD ORDER";
                    byte[] writeBytes = outLine.getBytes();
                    ByteBuffer writeBuffer = ByteBuffer.allocate(writeBytes.length);
                    writeBuffer.put(writeBytes);
                    writeBuffer.flip();
                    channel.write(writeBuffer);
                } else if (read < 0) {
                    selectionKey.cancel();
                    channel.close();
                }
            }
        }
    }
}
