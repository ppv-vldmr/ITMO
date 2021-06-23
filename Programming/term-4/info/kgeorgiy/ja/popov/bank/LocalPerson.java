package info.kgeorgiy.ja.popov.bank;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class LocalPerson implements Person, Serializable {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final Map<String, AccountBase> accounts;

    public LocalPerson(int id, String firstName, String lastName, Map<String, AccountBase> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
    }

    @Override
    public int getPassportId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    Set<String> getAccounts() {
        return accounts.keySet();
    }

    Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    void addAccount(String name, AccountBase AccountBase) {
        accounts.put(name, AccountBase);
    }
}