package info.kgeorgiy.ja.popov.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Vladimir Popov
 */
public class HelloUDPNonblockingServer implements HelloServer {
    private static final int TIMEOUT = 500;
    private static final int MAX_BUFFER_SIZE = 100;

    ExecutorService manager;
    Selector selector;
    DatagramChannel channel;

    @Override
    public void start(int port, int threads) {
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        try {
            selector = Selector.open();

            channel = Utils.openDatagramChannel();
            channel.bind(socketAddress);

            channel.register(selector, SelectionKey.OP_READ, new Context());
            manager = Executors.newSingleThreadExecutor();
            manager.submit(() -> {
                try {
                    start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error during start");
        }
    }

    private static class Context {
        ByteBuffer buffer;
        String data;
        SocketAddress clientAddress;


        void setData(String s) {
            data = s;
        }

        void updateBuffer() {
            buffer = ByteBuffer.allocate(data.length() + "Hello, ".length());
            buffer.put(("Hello, " + data).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void start() throws IOException {
        ByteBuffer destination = ByteBuffer.allocate(MAX_BUFFER_SIZE);
        while (channel.isOpen()) {
            selector.select(TIMEOUT);
            for (final Iterator<SelectionKey> i = selector.selectedKeys().iterator(); i.hasNext(); ) {
                final SelectionKey key = i.next();
                if (key.isReadable()) {
                    DatagramChannel channel = (DatagramChannel) key.channel();
                    Context context = (Context) key.attachment();
                    try {
                        destination.clear();
                        context.clientAddress = channel.receive(destination);
                        if (context.clientAddress != null) {
                            String msg = Utils.readStringFromBuffer(destination);
                            context.setData(msg);
                            context.updateBuffer();
                            key.interestOps(SelectionKey.OP_WRITE);
                        }
                    } catch (IOException e) {
                        System.err.println("Error in sending/receiving data" + e.getMessage());
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                }
                if (key.isValid() && key.isWritable()) {
                    DatagramChannel channel = (DatagramChannel) key.channel();
                    Context context = (Context) key.attachment();
                    context.buffer.flip();
                    channel.send(context.buffer, context.clientAddress);
                    key.interestOps(SelectionKey.OP_READ);
                }
                i.remove();
            }
        }
    }

    @Override
    public void close() {
        try {
            selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager.shutdown();
        try {
            manager.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.err.println("Wrong arguments, should be 2 integer numbers");
            return;
        }

        try (HelloServer server = new HelloUDPNonblockingServer()) {
            server.start(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            System.err.println("Wrong arguments, should be 2 integer numbers");
        }
    }


}
