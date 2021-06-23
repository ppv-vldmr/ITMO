package info.kgeorgiy.ja.popov.bank;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Objects;


public class Client {
    public static void main(final String... args) throws RemoteException {
        final Bank bank;
        String firstName;
        String lastName;
        String accountId;
        int passportId;
        int change;

        try {
            // Возвращает ссылку на реестр удаленных объектов для локального хоста на указанном порту.
            Registry registry = LocateRegistry.getRegistry(8889);
            // Возвращает удаленную ссылку, привязанную к указанному имени в этом реестре.
            bank = (Bank) registry.lookup("bank");
        } catch (NotBoundException e) {
            error(e, "bank is not bound");
            return;
        }
        if (args.length != 5) {
            System.out.println("Expected five arguments, found " + args.length);
            return;
        }
        if (Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.out.println("Expected non-null arguments");
            return;
        }
        try {
            firstName = args[0];
            lastName = args[1];
            passportId = Integer.parseInt(args[2]);
            accountId = args[3];
            change = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            error(e, "An error occurred while parsing args");
            return;
        }

        Person person = bank.getRemotePerson(passportId);
        if (person == null) {
            System.out.println("Creating new person by id " + passportId);
            bank.createPerson(passportId, firstName, lastName);
            person = bank.getRemotePerson(passportId);
        }

        if (!bank.getPerson(passportId, firstName, lastName)) {
            System.out.println("Incorrect person data");
            return;
        }

        Account account = bank.getAccount(person, accountId);
        System.out.println("Account id: " + account.getId());
        System.out.println("Money: " + account.getMoney());
        System.out.println("Changing amount...");
        account.setMoney(account.getMoney() + change);
        System.out.println("Money: " + account.getMoney());
    }

    private static void error(Exception e, String message) {
        System.err.println(message);
        System.err.println("Exception message: " + e.getMessage());
    }
}