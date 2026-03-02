package ru.inno.lesson;

import java.util.Arrays;

/**
 * Класс, представляющий клиента банка.
 */
public class Client {
    private String firstName;
    private String lastName;
    private String passport; // серия и номер паспорта (можно хранить одной строкой)
    private Account[] accounts;

    // Конструктор без счетов (массив нулевой длины)
    public Client(String firstName, String lastName, String passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.accounts = new Account[0];
    }

    // Конструктор с массивом счетов
    public Client(String firstName, String lastName, String passport, Account[] accounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.accounts = accounts;
    }

    // Метод получения счёта по номеру
    public Account getAccountByNumber(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null; // если не найден
    }

    // Метод возврата всех счетов
    public Account[] getAllAccounts() {
        return accounts; // возвращаем ссылку на массив (можно скопировать, но по условию "возвращающий массив всех счетов")
    }

    // Суммарный остаток на всех счетах
    public double getTotalBalance() {
        double total = 0.0;
        for (Account acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    // Массив счетов с положительным остатком
    public Account[] getPositiveAccounts() {
        // сначала подсчитаем количество положительных
        int count = 0;
        for (Account acc : accounts) {
            if (acc.getBalance() > 0) {
                count++;
            }
        }
        // создаём массив и заполняем
        Account[] result = new Account[count];
        int index = 0;
        for (Account acc : accounts) {
            if (acc.getBalance() > 0) {
                result[index++] = acc;
            }
        }
        return result;
    }

    // Удаление счёта по номеру
    public void removeAccount(String accountNumber) {
        int index = -1;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            // счёт не найден, ничего не делаем
            return;
        }
        // создаём новый массив без удаляемого элемента
        Account[] newAccounts = new Account[accounts.length - 1];
        System.arraycopy(accounts, 0, newAccounts, 0, index);
        System.arraycopy(accounts, index + 1, newAccounts, index, accounts.length - index - 1);
        accounts = newAccounts;
    }

    // Добавление счёта в конец массива
    public void addAccount(Account account) {
        Account[] newAccounts = new Account[accounts.length + 1];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        newAccounts[accounts.length] = account;
        accounts = newAccounts;
    }

    // Уменьшение остатка счёта на указанную сумму
    public void decreaseBalance(Account account, double amount) {
        // Проверим, принадлежит ли счёт клиенту (по желанию)
        if (!isAccountBelongsToClient(account)) {
            System.out.println("Ошибка: счёт не принадлежит клиенту.");
            return;
        }
        account.setBalance(account.getBalance() - amount);
    }

    // Увеличение остатка счёта на указанную сумму
    public void increaseBalance(Account account, double amount) {
        if (!isAccountBelongsToClient(account)) {
            System.out.println("Ошибка: счёт не принадлежит клиенту.");
            return;
        }
        account.setBalance(account.getBalance() + amount);
    }

    // Вспомогательный метод для проверки принадлежности счёта
    private boolean isAccountBelongsToClient(Account account) {
        for (Account acc : accounts) {
            if (acc == account) { // сравниваем по ссылке (можно и по номеру)
                return true;
            }
        }
        return false;
    }

    // Для удобства вывода информации о клиенте
    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", accounts=" + Arrays.toString(accounts) +
                '}';
    }
}