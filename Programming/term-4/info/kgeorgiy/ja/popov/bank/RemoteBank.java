package info.kgeorgiy.ja.popov.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class RemoteBank implements Bank, Remote {
    private final int port;
    private final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<Integer, Person> persons = new ConcurrentHashMap<>();
    private final ConcurrentMap<Integer, Set<String>> accountsByPassportId = new ConcurrentHashMap<>();

    public RemoteBank(final int port) {
        this.port = port;
    }

    @Override
    public boolean createAccount(Person person, String id) throws RemoteException {
        if (person == null || id == null) {
            return false;
        }
        String accountId = person.getPassportId() + ":" + id;
        Account account = accounts.get(accountId);
        if (account != null) {
            return false;
        }
        System.out.println("Creating an account " + accountId);
        account = new AccountBase(id);
        accounts.put(accountId, account);
        // :NOTE-2: data race
        // Экспортирует удаленный объект, чтобы сделать его доступным для приема входящих вызовов, используя определенный предоставленный порт.
        UnicastRemoteObject.exportObject(account, port);
        accountsByPassportId.get(person.getPassportId()).add(id);
        if (person instanceof LocalPerson) {
            ((LocalPerson) person).addAccount(id, new AccountBase(id));
        }
        return true;
    }

    @Override
    public Account getAccount(Person person, String id) throws RemoteException {
        if (person == null || id == null) {
            return null;
        }
        String accountId = createAccountId(person, id);
        Account account = accounts.get(accountId);
        if (account == null) {
            createAccount(person, id.split(":")[0]);
            account = accounts.get(accountId);
        }
        System.out.println("Getting account " + accountId);
        if (person instanceof LocalPerson) {
            return ((LocalPerson) person).getAccount(id);
        }
        return account;
    }

    private String createAccountId(Person person, String id) throws RemoteException {
        return person.getPassportId() + ":" + id;
    }

    private boolean checkParameters(int passportId, String firstName, String lastName) {
        return passportId >= 0 && firstName != null && lastName != null;
    }

    @Override
    public boolean createPerson(int passportId, String firstName, String lastName) throws RemoteException {
        if (!checkParameters(passportId, firstName, lastName)) {
            return false;
        }
        Person result = persons.get(passportId);
        if (result != null) {
            return false;
        }
        System.out.println("Creating person " + firstName + " " + lastName + " with ID " + passportId + ".");

        result = new RemotePerson(passportId, firstName, lastName);
        persons.put(passportId, result);
        accountsByPassportId.put(passportId, new ConcurrentSkipListSet<>());
        // Экспортирует удаленный объект, чтобы сделать его доступным для приема входящих вызовов, используя определенный предоставленный порт.
        UnicastRemoteObject.exportObject(result, port);
        return true;
    }

    @Override
    public boolean getPerson(int passportId, String firstName, String lastName) throws RemoteException {
        if (!checkParameters(passportId, firstName, lastName)) {
            return false;
        }
        System.out.println("Checking person " + firstName + " " + lastName + " with ID " + passportId + ".");
        Person person = persons.get(passportId);
        return person != null && person.getFirstName().equals(firstName) && person.getLastName().equals(lastName);
    }

    @Override
    public Person getLocalPerson(int passportId) throws RemoteException {
        if (passportId < 0) {
            return null;
        }
        Person person = persons.get(passportId);
        if (person == null) {
            return null;
        }
        System.out.println("Getting local person by passport " + passportId + ".");

        Set<String> accountNames = getAccountsByPerson(person);
        Map<String, AccountBase> accounts = new ConcurrentHashMap<>();
        accountNames.forEach(x -> {
            try {
                Account account = getAccount(person, x);
                accounts.put(x, new AccountBase(account.getId(), account.getMoney()));
            } catch (RemoteException e) {
                System.out.println("An error occurred while creating the local accounts.");
            }
        });
        return new LocalPerson(person.getPassportId(), person.getFirstName(), person.getLastName(), accounts);
    }

    @Override
    public Person getRemotePerson(int passportId) {
        if (passportId < 0) {
            return null;
        }
        System.out.println("Getting remote person by passport " + passportId + ".");
        return persons.get(passportId);
    }

    @Override
    public Set<String> getAccountsByPerson(Person person) throws RemoteException {
        if (person == null) {
            return null;
        }
        System.out.println("Getting accounts for person by passport " + person.getPassportId() + ".");
        if (person instanceof LocalPerson) {
            return ((LocalPerson) person).getAccounts();
        }
        return accountsByPassportId.get(person.getPassportId());
    }
}
