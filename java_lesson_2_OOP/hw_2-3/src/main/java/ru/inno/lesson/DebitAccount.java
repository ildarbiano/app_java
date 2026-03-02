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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DebitAccount that = (DebitAccount) obj;
        return getAccountNumber() == that.getAccountNumber();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + getAccountNumber();
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
