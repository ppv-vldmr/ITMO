package info.kgeorgiy.ja.popov.bank;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import static org.junit.Assert.*;

// :NOTE-2: multi-threaded tests
@RunWith(JUnit4.class)
public class BankTest {

    private static final int PORT = 8890;
    private static Bank bank;

    private final int id = 1;
    private final String name = "first";
    private final String surname = "second";
    private final String accountName = "accountName";

    // Registry это удаленный интерфейс к простому реестру удаленных объектов, который предоставляет методы для
    // хранения и извлечения ссылок на удаленные объекты, связанные с произвольными строковыми именами.
    // Методы bind, unbind и rebind используются для изменения привязок имен в реестре, а методы поиска и списка
    // используются для запроса текущих привязок имен.
    private static Registry registry;

    @BeforeClass
    public static void beforeAll() throws RemoteException {
        registry = LocateRegistry.createRegistry(PORT);
    }

    @Before
    public void beforeEach() throws RemoteException {
        bank = new RemoteBank(PORT);
        // Экспортирует удаленный объект, чтобы сделать его доступным для приема входящих вызовов, используя определенный предоставленный порт.
        Bank stub = (Bank) UnicastRemoteObject.exportObject(bank, PORT);
        // Заменяет привязку для указанного имени в этом реестре с предоставленным удаленным объектом.
        registry.rebind("bank", stub);
    }


    @Test
    public void changeAmount() throws RemoteException {
        // создаём 10 пользователей
        for (var i = 0; i < 10; ++i) {
            assertTrue(bank.createPerson(id + i, name + i, surname + i));
            bank.getRemotePerson(id + i);
        }
        // каждому пользователю создаем 10 счетов
        for (var i = 0; i < 10; ++i) {
            Person person = bank.getRemotePerson(id + i);
            for (var j = 0; j < 10; ++j) {
                bank.createAccount(person, "Account" + j);
            }
        }
        // изначально баланс равен 0, добавим 200
        changingAmountOnValue(200, 200);
        // вычтем 200 и проверим что баланс 0
        changingAmountOnValue(-200, 0);
    }

    private void changingAmountOnValue(int value, int resultBalance) throws RemoteException {
        for (var i = 0; i < 10; ++i) {
            Person person = bank.getRemotePerson(id + i);
            for (var j = 0; j < 10; ++j) {
                Account account = bank.getAccount(person, "Account" + j);
                System.out.println("Account id: " + account.getId());
                System.out.println("Money: " + account.getMoney());
                System.out.println("Changing amount...");
                account.setMoney(account.getMoney() + value);
                System.out.println("Money: " + account.getMoney());
                assertEquals(resultBalance, account.getMoney());
            }
        }
    }

    @Test
    public void createOneRemotePerson() throws RemoteException {
        // Проверяем, что успешно создали пользователя
        assertTrue(bank.createPerson(id, name, surname));
        Person remotePerson = bank.getRemotePerson(id);
        // Проверяем, что все его данные верные
        checkPersonParameters(remotePerson);
    }

    private void checkPersonParameters(Person person) throws RemoteException {
        assertEquals(person.getPassportId(), id);
        assertEquals(person.getFirstName(), name);
        assertEquals(person.getLastName(), surname);
    }

    private void checkPersonParameters(Person person, int id, String name, String surname) throws RemoteException {
        assertEquals(person.getPassportId(), id);
        assertEquals(person.getFirstName(), name);
        assertEquals(person.getLastName(), surname);
    }
    
    @Test
    public void createOneLocalPerson() throws RemoteException {
        assertTrue(bank.createPerson(id, name, surname));
        Person localPerson = bank.getLocalPerson(id);
        checkPersonParameters(localPerson);
    }

    @Test
    public void duplicatedPersonAccounts() throws RemoteException {
        bank.createPerson(id, name, surname);
        Person remotePerson = bank.getRemotePerson(id);
        // Пытаемся создать 100 аккаунтов, но должно создастся только 10
        for (int i = 0; i < 100; ++i) {
            bank.createAccount(remotePerson, Integer.toString(i % 10));
        }
        assertEquals(bank.getAccountsByPerson(remotePerson).size(), 10);
    }

    @Test
    public void createOnePersonTwice() throws RemoteException {
        // Аналогично предыдущему тесту
        assertTrue(bank.createPerson(id, name, surname));
        assertFalse(bank.createPerson(id, name, surname));
    }

    @Test
    public void createManyPersons() throws RemoteException {
        for (int i = 0; i < 1000; i++) {
            // Создаём уникального пользователя
            assertTrue(bank.createPerson(i, name + i, surname + i));

            // Проверяем его данные
            Person remotePerson = bank.getRemotePerson(i);
            checkPersonParameters(remotePerson, i, name + i, surname + i);

            // Проверяем его данные
            Person localPerson = bank.getLocalPerson(i);
            checkPersonParameters(localPerson, i, name + i, surname + i);
        }
    }

    @Test
    public void createManyAccounts() throws RemoteException {
        int peopleCount = 100;
        int randomMax = 100;
        Random random = new Random();
        for (int i = 0; i < peopleCount; i++) {
            bank.createPerson(i, name, surname);
            Person remotePerson = bank.getRemotePerson(i);
            int accountCount = Math.abs(random.nextInt(randomMax));
            for (int j = 0; j < accountCount; j++) {
                bank.createAccount(remotePerson, accountName + j);
            }
            assertEquals(bank.getAccountsByPerson(remotePerson).size(), accountCount);
        }
    }
    
    @Test
    public void getPersonWithoutCreating() throws RemoteException {
        assertNull(bank.getLocalPerson(1234));
    }

    @Test
    public void createOneAccountRemotePerson() throws RemoteException {
        bank.createPerson(id, name, surname);
        // Создадим одного удалённого пользователя, а затем аккаунт у него
        Person remotePerson = bank.getRemotePerson(id);
        assertTrue(bank.createAccount(remotePerson, accountName));
        Account account = bank.getAccount(remotePerson, accountName);
        // И проверим, что все данные для аккаунта верные
        assertEquals(account.getId(), accountName);
        assertEquals(account.getMoney(), 0);
    }

    @Test
    public void createOneAccountLocalPerson() throws RemoteException {
        bank.createPerson(id, name, surname);
        // Аналогично предыдущему, только создаём локального пользователя
        Person localPerson = bank.getLocalPerson(id);
        assertTrue(bank.createAccount(localPerson, accountName));
        Account account = bank.getAccount(localPerson, accountName);
        assertEquals(account.getId(), accountName);
        assertEquals(account.getMoney(), 0);
    }

    @Test
    public void createOneAccountRemotePersonTwice() throws RemoteException {
        bank.createPerson(id, name, surname);
        createAccountTwice(bank.getRemotePerson(id));
    }

    @Test
    public void createOneAccountLocalPersonTwice() throws RemoteException {
        bank.createPerson(id, name, surname);
        createAccountTwice(bank.getLocalPerson(id));
    }

    private void createAccountTwice(Person person) throws RemoteException {
        assertTrue(bank.createAccount(person, accountName));
        assertFalse(bank.createAccount(person, accountName));
    }

    @Test
    public void getAccountWithoutCreating() throws RemoteException {
        bank.createPerson(id, name, surname);

        Person person = bank.getRemotePerson(id);
        Account account = bank.getAccount(person, "1234");
        assertEquals(account.getMoney(), 0);
        account.setMoney(account.getMoney() + 322);
        assertEquals(account.getMoney(), 322);
        account.setMoney(account.getMoney() - 322);
        assertEquals(account.getMoney(), 0);
    }

    @Test
    public void setAccountAmountRemotePerson() throws RemoteException {
        int amount = 542;
        bank.createPerson(id, name, surname);

        Person localPerson = bank.getLocalPerson(id);
        Person remotePerson = bank.getRemotePerson(id);

        bank.createAccount(remotePerson, accountName);
        Account account = bank.getAccount(remotePerson, accountName);

        // Счёт только у удалённого пользователя, поэтому
        assertNull(bank.getAccount(localPerson, accountName));
        localPerson = bank.getLocalPerson(id);

        account.setMoney(amount);

        account = bank.getAccount(remotePerson, accountName);
        assertEquals(account.getId(), accountName);
        assertEquals(account.getMoney(), amount);

        account = bank.getAccount(localPerson, accountName);
        assertEquals(account.getMoney(), 0);

        localPerson = bank.getLocalPerson(id);
        account = bank.getAccount(localPerson, accountName);
        assertEquals(account.getMoney(), amount);
    }

    @Test
    public void setAccountAmountLocalPerson() throws RemoteException {
        final int amount = 542;
        bank.createPerson(id, name, surname);

        Person localPerson = bank.getLocalPerson(id);
        bank.createAccount(localPerson, accountName);
        Account account = bank.getAccount(localPerson, accountName);
        account.setMoney(amount);

        assertEquals(account.getMoney(), amount);

        Person remotePerson = bank.getRemotePerson(id);
        account = bank.getAccount(remotePerson, accountName);
        assertEquals(account.getMoney(), 0);
    }


    @Test
    public void checkAndCreateOnePerson() throws RemoteException {
        assertFalse(bank.getPerson(id, name + id, surname + id));
        bank.createPerson(id, name + id, surname + id);
        assertTrue(bank.getPerson(id, name + id, surname + id));
    }


    @Test
    public void checkAndCreateManyPerson() throws RemoteException {
        int count = 1000;
        for (int i = 0; i < count; ++i) {
            assertFalse(bank.getPerson(i, name + i, surname + i));
            bank.createPerson(i, name + i, surname + i);
            assertTrue(bank.getPerson(i, name + i, surname + i));
        }
    }

    @Test
    public void checkSomeUserCreateManyAccount() throws RemoteException {
        int peopleCount = 100;
        int accountCount = 100;
        bank.createPerson(id, name, surname);
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < peopleCount; ++i) {
            people.add(bank.getRemotePerson(id));
        }
        for (int i = 0; i < accountCount; i++) {
            for (int j = 0; j < peopleCount; j++) {
                assertTrue(bank.createAccount(people.get(j), "person" + j + "account" + i));
            }
        }
        for (int i = 0; i < peopleCount; i++) {
            assertEquals(bank.getAccountsByPerson(people.get(i)).size(), accountCount * peopleCount);
        }
    }

    @Test
    public void checkSomeUserSetAmount() throws RemoteException {
        int peopleCount = 100;
        int accountCount = 100;
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < peopleCount; ++i) {
            bank.createPerson(i, name, surname);
            people.add(bank.getRemotePerson(i));
        }
        Map<String, Integer> answer = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < accountCount; i++) {
            for (int j = 0; j < peopleCount; j++) {
                final String accountId = "person" + j + "account" + i;
                bank.createAccount(people.get(j), accountId);
                Account account = bank.getAccount(people.get(j), accountId);
                int value = Math.abs(random.nextInt());
                account.setMoney(value);
                answer.put(accountId, value);
            }
        }
        for (int i = 0; i < accountCount; i++) {
            for (int j = 0; j < peopleCount; j++) {
                final String accountId = "person" + j + "account" + i;
                assertEquals(bank.getAccount(people.get(j), accountId).getMoney(), answer.get(accountId).intValue());
            }
        }
    }
}