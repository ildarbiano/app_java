package ru.inno.lesson;

/**
 * Перечисление валют с фиксированными курсами к рублю (п.2)
 */
public enum Currency {
    USD(100.0),   // 1 USD = 100 RUB
    EUR(110.0),   // 1 EUR = 110 RUB
    JPY(0.9),     // 1 JPY = 0.9 RUB
    TRY(5.0),     // 1 TRY = 5 RUB
    AED(27.0),    // 1 AED = 27 RUB
    RUB(1.0);     // 1 RUB = 1 RUB

    private final double rateToRUB;

    Currency(double rateToRUB) {
        this.rateToRUB = rateToRUB;
    }

    public double getRateToRUB() {
        return rateToRUB;
    }
}
