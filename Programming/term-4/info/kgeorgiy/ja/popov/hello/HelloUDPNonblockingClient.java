package info.kgeorgiy.ja.popov.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;

/**
 * @author Vladimir Popov
 */
public class HelloUDPNonblockingClient implements HelloClient {

    private static final int TIMEOUT = 50;
    private static final int MAX_BUFFER_SIZE = 100;

    @Override
    public void run(String host, int port, String prefix, int threads, int requests) {

        InetAddress address;
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.err.println("Wrong host name " + e.getMessage());
            return;
        }
        SocketAddress socketAddress = new InetSocketAddress(address, port);
        try (
                Selector selector = Selector.open();
        ) {
            run(selector, socketAddress, prefix, threads, requests);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error");
        }

    }


    private static class Context {
        private final String prefix;
        private final int threadID;

        private ByteBuffer buffer;
        private int currentRequestID;

        private int remaining;

        Context(String prefix, int threadID, int requests) {
            this.prefix = prefix;
            this.threadID = threadID;
            remaining = requests;
            currentRequestID = 0;
        }

        boolean isSentAll() {
            return remaining == 0;
        }

        void receive() {
            remaining--;
            currentRequestID++;
        }

        String getMessage() {
            return prefix + threadID + "_" + currentRequestID;
        }

        void updateBuffer() {
            buffer = ByteBuffer.wrap(getMessage().getBytes(StandardCharsets.UTF_8));
            buffer.put(getMessage().getBytes(StandardCharsets.UTF_8));
        }

        ByteBuffer getBuffer() {
            return buffer;
        }
    }

    private void run(final Selector selector, final SocketAddress socketAddress, String prefix, int threads, int requests) throws IOException {
        for (int thread = 0; thread < threads; ++thread) {
            DatagramChannel channel = Utils.openDatagramChannel();
            channel.connect(socketAddress);
            channel.register(selector, SelectionKey.OP_WRITE, new Context(prefix, thread, requests));
        }

        int remaining = threads;
        ByteBuffer dst = ByteBuffer.allocate(MAX_BUFFER_SIZE);
        while (remaining > 0) {
            int ready = selector.select(TIMEOUT);
            if (ready == 0) {
                for (final SelectionKey key : selector.keys()) {
                    key.interestOps(SelectionKey.OP_WRITE);
                }
            } else {
                for (final SelectionKey key : selector.selectedKeys()) {
                    if (key.isReadable()) {
                        DatagramChannel channel = (DatagramChannel) key.channel();
                        Context context = (Context) key.attachment();
                        try {
                            dst.clear();
                            SocketAddress address = channel.receive(dst);
                            if (address != null) {
                                String msg = Utils.readStringFromBuffer(dst);
                                if (msg.contains(context.getMessage())) {
                                    System.out.println("RECEIVED: " + msg);
                                    context.receive();
                                    key.interestOps(SelectionKey.OP_WRITE);
                                    if (context.isSentAll()) {
                                        --remaining;
                                        key.cancel();
                                        channel.close();
                                    }
                                } else {
                                    key.interestOps(SelectionKey.OP_WRITE);
                                    System.out.println("Wrong message");
                                }
                            } else {
                                System.out.println("Not received");
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
                        context.updateBuffer();
                        context.getBuffer().flip();
                        int bytesSent = channel.send(context.getBuffer(), socketAddress);
                        if (bytesSent != 0) {
                            System.out.println("SENT: " + context.getMessage() + " bytes: " + bytesSent);
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
                selector.selectedKeys().clear();
            }
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length != 5) {
            return;
        }

        HelloUDPNonblockingClient client = new HelloUDPNonblockingClient();
        try {
            client.run(args[0], Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        } catch (NumberFormatException e) {
            System.err.println("Wrong arguments, should be \"host, port, prefix, threads, requests\"");
        }
    }

}
