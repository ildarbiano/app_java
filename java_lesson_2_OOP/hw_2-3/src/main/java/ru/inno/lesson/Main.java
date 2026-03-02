package ru.inno.lesson;

public class Main {
    public static void main(String[] args) {
        // Генерация номеров счетов
        int accNum1 = AccountNumberGenerator.getNext();
        int accNum2 = AccountNumberGenerator.getNext();

        // Создание дебетового счёта в USD
        DebitAccount debit = new DebitAccount(accNum1, 1000, 5, Currency.USD);
        System.out.println("Дебетовый счёт: " + debit.getBalance() + " " + debit.getCurrency());
        System.out.println(debit); // использует toString

        // Создание кредитного счёта в RUB с лимитом -5000 и ставкой 15%
        CreditAccount credit = new CreditAccount(accNum2, 2000, 10, Currency.RUB, 15.0, -5000);
        credit.deductCommission(); // комиссия уходит в начисленные
        credit.deposit(100);       // погашение комиссии и процентов
        credit.accrueInterest();   // начисление процентов за день
        System.out.println("Кредитный счёт: баланс=" + credit.getBalance() + " " + credit.getCurrency() +
                ", начисленные комиссии=" + credit.getAccruedCommissions() +
                ", начисленные проценты=" + credit.getAccruedInterest());

        // Проверка исключения
        try {
            credit.withdraw(10000); // превышение лимита
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e);
        }
    }
}
