package ru.inno.lesson;

import java.util.Arrays;

/**
 * Точка входа для тестирования классов Account и Client.
 */
public class Main {
    public static void main(String[] args) {
        // Создаём несколько счетов
        Account acc1 = new Account("40817810000000000001", 15000.50);
        Account acc2 = new Account("40817810000000000002", -500.0); // отрицательный остаток (овердрафт)
        Account acc3 = new Account("40817810000000000003", 0.0);
        Account acc4 = new Account("40817810000000000004", 23000.0);

        // Создаём клиента с двумя счетами
        Client client = new Client("Иван", "Петров", "4510 123456", new Account[]{acc1, acc2});

        // Выводим информацию о клиенте
        System.out.println("Информация о клиенте:");
        System.out.println(client);

        // 1. Получение счёта по номеру
        System.out.println("\n--- Получение счёта по номеру ---");
        String searchNumber = "40817810000000000001";
        Account found = client.getAccountByNumber(searchNumber);
        System.out.println("Счёт с номером " + searchNumber + ": " + found);

        searchNumber = "несуществующий";
        found = client.getAccountByNumber(searchNumber);
        System.out.println("Счёт с номером " + searchNumber + ": " + found);

        // 2. Массив всех счетов
        System.out.println("\n--- Все счета клиента ---");
        Account[] all = client.getAllAccounts();
        System.out.println(Arrays.toString(all));

        // 3. Суммарный остаток
        System.out.println("\n--- Суммарный остаток ---");
        System.out.println("Сумма на всех счетах: " + client.getTotalBalance() + " руб.");

        // 4. Счета с положительным остатком
        System.out.println("\n--- Счета с положительным остатком ---");
        Account[] positive = client.getPositiveAccounts();
        System.out.println(Arrays.toString(positive));

        // 5. Добавление нового счёта
        System.out.println("\n--- Добавление счёта ---");
        client.addAccount(acc3);
        System.out.println("После добавления acc3:");
        System.out.println(client);

        // 6. Удаление счёта по номеру
        System.out.println("\n--- Удаление счёта по номеру ---");
        client.removeAccount("40817810000000000002");
        System.out.println("После удаления счёта с номером 40817810000000000002:");
        System.out.println(client);

        // 7. Увеличение остатка
        System.out.println("\n--- Увеличение остатка ---");
        System.out.println("До увеличения: " + acc1);
        client.increaseBalance(acc1, 5000.0);
        System.out.println("После увеличения на 5000: " + acc1);

        // 8. Уменьшение остатка
        System.out.println("\n--- Уменьшение остатка ---");
        System.out.println("До уменьшения: " + acc1);
        client.decreaseBalance(acc1, 3000.0);
        System.out.println("После уменьшения на 3000: " + acc1);

        // 9. Попытка уменьшить остаток на счёте, не принадлежащем клиенту
        System.out.println("\n--- Попытка операции с чужим счётом ---");
        Account foreignAcc = new Account("40817810000000000999", 1000.0);
        client.decreaseBalance(foreignAcc, 500.0); // будет сообщение об ошибке

        // 10. Проверка изменения номера счёта и остатка через сеттеры Account
        System.out.println("\n--- Изменение номера счёта и остатка через сеттеры Account ---");
        System.out.println("Счёт acc3 до изменений: " + acc3);
        acc3.setAccountNumber("40817810000000000333");
        acc3.setBalance(777.77);
        System.out.println("Счёт acc3 после изменений: " + acc3);

        // 11. Создание клиента только с именем, фамилией, паспортом (без счетов)
        System.out.println("\n--- Клиент без счетов ---");
        Client client2 = new Client("Петр", "Сидоров", "1234 567890");
        System.out.println(client2);
        System.out.println("Суммарный остаток: " + client2.getTotalBalance());
        System.out.println("Счета с положительным остатком: " + Arrays.toString(client2.getPositiveAccounts()));
    }
}