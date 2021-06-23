package info.kgeorgiy.ja.popov.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Account extends Remote {
    String getId() throws RemoteException;

    int getMoney() throws RemoteException;

    void setMoney(int amount) throws RemoteException;
}