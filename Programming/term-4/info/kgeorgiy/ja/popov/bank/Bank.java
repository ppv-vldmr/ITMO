package info.kgeorgiy.ja.popov.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Bank extends Remote {

    boolean createAccount(Person person, String id) throws RemoteException;

    Account getAccount(Person person, String id) throws RemoteException;

    boolean createPerson(int passportId, String firstName, String lastName) throws RemoteException;

    boolean getPerson(int passportId, String firstName, String lastName) throws RemoteException;

    Person getLocalPerson(int passportId) throws RemoteException;

    Person getRemotePerson(int passportId) throws RemoteException;

    Set<String> getAccountsByPerson(Person person) throws RemoteException;
}
