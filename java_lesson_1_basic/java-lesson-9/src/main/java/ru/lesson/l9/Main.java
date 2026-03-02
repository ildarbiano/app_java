package ru.lesson.l9;

public class Main {
    public static void main(String[] args) {
        ArrayOperations arrayOps = new ArrayOperations();

        System.out.println("=== Generated array ===");
        arrayOps.printArray();
        System.out.println();

        System.out.println("1. Count of numbers multiples of three: " +
                arrayOps.countMultiplesOfThree());

        System.out.println("2. Sum of positive numbers: " +
                arrayOps.sumOfPositiveNumbers());

        int maxPosIndex = arrayOps.indexOfMaxPositive();
        System.out.println("3. Index of maximum positive number: " +
                (maxPosIndex != -1 ? maxPosIndex : "No positive numbers"));

        System.out.println("4. Array contains zero values: " +
                arrayOps.hasZeroValues());

        System.out.println("5. List output of array elements with even indexes (numbers):");
        arrayOps.printEvenIndexElements();
    }
}
