package info.kgeorgiy.ja.popov.bank;

import java.io.Serializable;

public class AccountBase implements Account, Serializable {
    private final String id;
    private int amount;

    public AccountBase(final String id) {
        this.id = id;
        amount = 0;
    }

    public AccountBase(final String id, final int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public synchronized int getMoney() {
        System.out.println("Getting amount of money for account " + id);
        return amount;
    }

    @Override
    public synchronized void setMoney(int amount) {
        System.out.println("Setting amount of money for account " + id);
        this.amount = amount;
    }
}