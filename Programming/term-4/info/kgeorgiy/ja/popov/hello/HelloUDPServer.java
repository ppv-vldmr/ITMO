package info.kgeorgiy.ja.popov.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class HelloUDPServer implements HelloServer {

    private ExecutorService workers;
    private DatagramSocket socket;
    private int bufferSize;

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.out.println("Expected usage: HelloUDPServer [port] [threads]");
            return;
        }
        try {
            new HelloUDPServer().start(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            System.out.println("Incorrect number: " + e.getMessage());
        }
    }

    @Override
    public void start(int port, int threads) {
        try {
            socket = new DatagramSocket(port);
            workers = Executors.newFixedThreadPool(threads);
            bufferSize = socket.getReceiveBufferSize();
            IntStream.range(0, threads).forEach(i -> workers.submit(this::performTask));
        } catch (SocketException e) {
            System.out.println("Can't create socket");
        }

    }
    
    private void performTask() {
        try {
            while (!socket.isClosed()) {
                DatagramPacket request = new DatagramPacket(new byte[bufferSize], bufferSize);
                socket.receive(request);
                String requestName = Utils.getPacketData(request);
                DatagramPacket response = Utils.getSendPacket("Hello, " + requestName, request.getSocketAddress());
                socket.send(response);
            }
        } catch (IOException e) {
            if (!socket.isClosed()) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void close() {
        socket.close();
        workers.shutdownNow();
        try {
            if (!workers.awaitTermination( 100, TimeUnit.MILLISECONDS)) {
                System.out.println("Can't shutdown threads");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}