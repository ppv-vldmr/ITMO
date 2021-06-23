package info.kgeorgiy.ja.popov.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class HelloUDPClient implements HelloClient {

    public static void main(String[] args) {
        if (args == null || args.length != 5 || Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.out.println("Expected usage: HelloUDPClient [host or ip] [port] [prefix] [threads] [requests]");
            return;
        }
        try {
            new HelloUDPClient().run(args[0], Integer.parseInt(args[1]), args[2],
                    Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        } catch (NumberFormatException e) {
            System.out.println("Incorrect number: " + e.getMessage());
        }
    }

    @Override
    public void run(String name, int port, String prefix, int threads, int requests) {
        InetAddress address;
        try {
            address = InetAddress.getByName(name);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + name);
            return;
        }

        final SocketAddress receiver = new InetSocketAddress(address, port);
        final ExecutorService workers = Executors.newFixedThreadPool(threads);
        IntStream.range(0, threads).forEach(i -> workers.submit(() -> performTask(i, receiver, prefix, requests)));

        workers.shutdown();
        try {
            if (!workers.awaitTermination(threads * requests * 100, TimeUnit.SECONDS)) {
                System.out.println("Can't shutdown threads");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    private void performTask(int thread, SocketAddress destination, String prefix, int requests) {
        String requestFormat = "%s%d_%d";
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(100); // :NOTE: move to a const value
            for (int i = 0; i != requests; i++) {
                final String requestName = String.format(requestFormat, prefix, thread, i);

                final DatagramPacket request = Utils.getSendPacket(requestName, destination);

                final DatagramPacket response = Utils.getReceivePacket(socket);

                System.out.println(requestName);
                while (true) {

                    try {
                        socket.send(request);
                    } catch (IOException ignored) {
                        continue;
                    }

                    try {
                        socket.receive(response);
                    } catch (IOException ignored) {
                        continue;
                    }

                    String checkResponse = Utils.getPacketData(response);

                    if (checkResponse.contains(requestName)) {
                        System.out.println(checkResponse);
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Failed to create socket: " + e.getMessage());
        }
    }
}