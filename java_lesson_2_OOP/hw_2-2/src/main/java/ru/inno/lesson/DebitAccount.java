package ru.inno.lesson;

/**
 * Дебетовый счет (п.4) – не добавляет новой функциональности
 */
public class DebitAccount extends Account {
    public DebitAccount(int accountNumber, double balance, double commission) {
        super(accountNumber, balance, commission);
    }

    public DebitAccount(int accountNumber, double balance, double commission, Currency currency) {
        super(accountNumber, balance, commission, currency);
    }
}
