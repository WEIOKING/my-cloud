package cn.ply.cloud.java.IO.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/26
 * @ModifiedBy
 */
public class TimeClientHandle implements Runnable {
    private Selector selector;
    private SocketChannel socketChannel;
    private String ip;
    private int port;
    private boolean stop;

    public TimeClientHandle() {
    }

    public TimeClientHandle(String ip, int port, Selector selector) {
        try {
            this.ip = ip;
            this.port = port;
            this.selector = selector;
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
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
    }

    public void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel channel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (channel.finishConnect()) {
                    channel.register(selector, SelectionKey.OP_READ);
                    doWrite(channel, TimeServer.TIME_ORDER);
                }
            }
            if (key.isReadable()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = channel.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    int remaining = byteBuffer.remaining();
                    byte[] bytes = new byte[remaining];
                    byteBuffer.get(bytes);
                    String s = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println(s);
                    stop = true;
                } if (read < 0) {
                    key.cancel();
                    channel.close();
                }
            }
        }
    }

    public void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(ip, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel, TimeServer.TIME_ORDER);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    public void doWrite(SocketChannel socketChannel, String out) throws IOException {
        byte[] bytes = out.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socketChannel.write(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            System.out.println("SELECT TIME");
        }
    }
}
