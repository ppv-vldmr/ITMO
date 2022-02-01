/**
 * Bank implementation.
 *
 * <p>:TODO: This implementation has to be made thread-safe.
 *
 * @author :TODO: Popov Vladimir
 */
public class BankImpl implements Bank {
    /**
     * Private account data structure.
     */
    static class Account {
        /**
         * Amount of funds in this account.
         */
        long amount;
    }

    /**
     * An array of accounts by index.
     */
    private final Account[] accountsOfClients;

    /**
     * Creates new bank instance.
     * @param n the number of accounts (numbered from 0 to n-1).
     */
    public BankImpl(int n) {
        accountsOfClients = new Account[n];
        for (int i = 0; i < n; i++) {
            accountsOfClients[i] = new Account();
        }
    }

    @Override
    public int getNumberOfAccounts() {
        return accountsOfClients.length;
    }

    /**
     * <p>:TODO: This method has to be made thread-safe.
     */
    @Override
    public long getAmount(int index) {
        return accountsOfClients[index].amount;
    }

    /**
     * <p>:TODO: This method has to be made thread-safe.
     */
    @Override
    public long getTotalAmount() {
        long total = 0;
        for (Account accountsOfClient : accountsOfClients) {
            total += accountsOfClient.amount;
        }
        return total;
    }

    /**
     * <p>:TODO: This method has to be made thread-safe.
     */
    @Override
    public long deposit(int index, long amount) {
        if (amount > 0) {
            Account current = accountsOfClients[index];
            if (amount <= MAX_AMOUNT && current.amount + amount <= MAX_AMOUNT) {
                current.amount += amount;
                return current.amount;
            } else {
                throw new IllegalStateException("Overflow");
            }
        } else {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        }
    }

    /**
     * <p>:TODO: This method has to be made thread-safe.
     */
    @Override
    public long withdraw(int index, long amount) {
        if (amount > 0) {
            Account current = accountsOfClients[index];
            if (current.amount - amount < 0) {
                throw new IllegalStateException("Underflow");
            }
            current.amount -= amount;
            return current.amount;
        } else {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        }
    }

    /**
     * <p>:TODO: This method has to be made thread-safe.
     */
    @Override
    public void transfer(int fromIndex, int toIndex, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        } else {
            if (fromIndex == toIndex) {
                throw new IllegalArgumentException("fromIndex == toIndex");
            } else {
                Account l = accountsOfClients[fromIndex];
                Account r = accountsOfClients[toIndex];
                if (amount > l.amount) {
                    throw new IllegalStateException("Underflow");
                } else  {
                    if (amount > MAX_AMOUNT || r.amount + amount > MAX_AMOUNT) {
                        throw new IllegalStateException("Overflow");
                    }
                }
                l.amount -= amount;
                r.amount += amount;
            }
        }
    }
}