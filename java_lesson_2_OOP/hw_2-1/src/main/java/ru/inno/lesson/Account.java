package ru.inno.lesson;

/**
 * Класс, представляющий банковский счёт.
 */
public class Account {
    private String accountNumber; // уникальный номер счёта
    private double balance;       // остаток средств в рублях

    // Конструктор с номером счёта (остаток = 0)
    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    // Конструктор с номером счёта и остатком
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Геттер для номера счёта
    public String getAccountNumber() {
        return accountNumber;
    }

    // Сеттер для номера счёта
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Геттер для остатка
    public double getBalance() {
        return balance;
    }

    // Сеттер для остатка
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}