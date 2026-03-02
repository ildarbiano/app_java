package ru.inno.lesson;

import java.time.Year;

/**
 * Кредитный счет (п.5)
 */
public class CreditAccount extends Account {
    private double interestRate;           // годовая процентная ставка (в процентах)
    private double limit;                  // кредитный лимит (в валюте счета)
    private double accruedInterest;         // начисленные проценты
    private double accruedCommissions;      // начисленные комиссионные

    // Конструкторы, аналогичные суперклассу (остальные поля = 0)
    public CreditAccount(int accountNumber, double balance, double commission) {
        super(accountNumber, balance, commission);
        this.interestRate = 0;
        this.limit = 0;
        this.accruedInterest = 0;
        this.accruedCommissions = 0;
    }

    public CreditAccount(int accountNumber, double balance, double commission, Currency currency) {
        super(accountNumber, balance, commission, currency);
        this.interestRate = 0;
        this.limit = 0;
        this.accruedInterest = 0;
        this.accruedCommissions = 0;
    }

    // Конструктор со всеми параметрами
    public CreditAccount(int accountNumber, double balance, double commission, Currency currency,
                         double interestRate, double limit) {
        super(accountNumber, balance, commission, currency);
        this.interestRate = interestRate;
        this.limit = limit;
        this.accruedInterest = 0;
        this.accruedCommissions = 0;
    }

    // Геттеры и сеттеры для процентной ставки и лимита
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    // Геттеры для начисленных процентов и комиссионных
    public double getAccruedInterest() {
        return accruedInterest;
    }

    public double getAccruedCommissions() {
        return accruedCommissions;
    }

    /**
     * Начисление процентов за один день.
     * Используется фактическое количество дней в текущем году.
     */
    public void accrueInterest() {
        int daysInYear = Year.now().length(); // 365 или 366
        if (balance < limit) {
            double dailyRate = interestRate / daysInYear / 100.0;
            double interest = (limit - balance) * dailyRate;
            accruedInterest += interest;
        }
    }

    /**
     * Переопределенный метод вычитания комиссии:
     * комиссия не списывается с баланса, а добавляется к начисленным комиссионным.
     */
    @Override
    public void deductCommission() {
        this.accruedCommissions += getCommission();
    }

    /**
     * Переопределенный метод пополнения:
     * средства сначала идут на погашение начисленных комиссионных,
     * затем начисленных процентов, затем на пополнение остатка.
     */
    @Override
    public void deposit(double amount) {
        double remaining = amount;

        // Погашаем начисленные комиссионные
        if (remaining > 0 && accruedCommissions > 0) {
            double payCommissions = Math.min(remaining, accruedCommissions);
            accruedCommissions -= payCommissions;
            remaining -= payCommissions;
        }

        // Погашаем начисленные проценты
        if (remaining > 0 && accruedInterest > 0) {
            double payInterest = Math.min(remaining, accruedInterest);
            accruedInterest -= payInterest;
            remaining -= payInterest;
        }

        // Остаток зачисляется на баланс
        if (remaining > 0) {
            balance += remaining;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CreditAccount that = (CreditAccount) obj;
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
        StringBuilder sb = new StringBuilder(super.toString());
        // Удаляем закрывающую скобку, чтобы добавить свои поля
        String superStr = sb.toString();
        sb.setLength(superStr.length() - 1); // убираем '}'
        sb.append(", interestRate=").append(interestRate);
        sb.append(", limit=").append(limit);
        sb.append(", accruedInterest=").append(accruedInterest);
        sb.append(", accruedCommissions=").append(accruedCommissions);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void withdraw(double amount) {
        double newBalance = balance - amount;
        if (newBalance < limit) {
            throw new InsufficientFundsException("Превышение кредитного лимита. Доступно: " + (balance - limit));
        }
        this.balance = newBalance;
    }
}
