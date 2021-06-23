package info.kgeorgiy.ja.popov.bank;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private final static int PORT = 8889;

    public static void main(final String... args) {
        Bank bank;
        try {
            bank = new RemoteBank(PORT);
            // Экспортирует удаленный объект, чтобы сделать его доступным для приема входящих вызовов, используя определенный предоставленный порт.
            Bank stub = (Bank) UnicastRemoteObject.exportObject(bank, PORT);
            // Создает и экспортирует экземпляр на локальном хосте, который принимает запросы на указанный порт.
            Registry registry = LocateRegistry.createRegistry(PORT);
            // Заменяет привязку для указанного имени в этом реестре с предоставленным удаленным объектом.
            registry.rebind("bank", stub);
        } catch (final RemoteException e) {
            System.out.println("Cannot export object: " + e.getMessage());
            return;
        }
        System.out.println("Server started");
    }
}