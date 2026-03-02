package ru.inno.lesson;

/**
 * Абстрактный класс счета (п.3)
 */
public abstract class Account {
    private int accountNumber;
    protected double balance;           // остаток (protected для доступа из подклассов)
    private double commission;           // комиссия за обслуживание
    private Currency currency;           // валюта счета

    // Конструктор с 3 параметрами (валюта по умолчанию RUB)
    public Account(int accountNumber, double balance, double commission) {
        this(accountNumber, balance, commission, Currency.RUB);
    }

    // Конструктор с 4 параметрами
    public Account(int accountNumber, double balance, double commission, Currency currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.commission = commission;
        this.currency = currency;
    }

    // Геттеры и сеттеры для комиссии
    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    // Геттер и сеттер для валюты (с пересчетом остатка и комиссии)
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency newCurrency) {
        if (this.currency == newCurrency) return;
        double oldRate = this.currency.getRateToRUB();
        double newRate = newCurrency.getRateToRUB();
        // Пересчет остатка и комиссии по курсу
        this.balance = this.balance * oldRate / newRate;
        this.commission = this.commission * oldRate / newRate;
        this.currency = newCurrency;
    }

    // Геттер для номера счета
    public int getAccountNumber() {
        return accountNumber;
    }

    // Геттер для остатка (сеттер отсутствует)
    public double getBalance() {
        return balance;
    }

    // Метод вычитания комиссии из остатка
    public void deductCommission() {
        this.balance -= this.commission;
    }

    // Метод списания суммы
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    // Метод пополнения счета
    public void deposit(double amount) {
        this.balance += amount;
    }
}
