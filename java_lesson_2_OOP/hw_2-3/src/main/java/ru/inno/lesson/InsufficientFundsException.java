package ru.inno.lesson;

/**
 * Исключение, выбрасываемое при попытке списания суммы, превышающей остаток или кредитный лимит.
 */
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InsufficientFundsException{message=" + getMessage() + "}";
    }
}
