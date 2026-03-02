package ru.lesson.l9;
import java.util.Random;

public class ArrayOperations {
    private long[] array;

    public ArrayOperations() {
        array = new long[15];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(31) - 15;
        }
    }

    public int countMultiplesOfThree() {
        int count = 0;
        for (long num : array) {
            if (num % 3 == 0) {
                count++;
            }
        }
        return count;
    }

    public long sumOfPositiveNumbers() {
        long sum = 0;
        for (long num : array) {
            if (num > 0) {
                sum += num;
            }
        }
        return sum;
    }

    public int indexOfMaxPositive() {
        int maxIndex = -1;
        long maxValue = Long.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0 && array[i] > maxValue) {
                maxValue = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public boolean hasZeroValues() {
        for (long num : array) {
            if (num == 0) {
                return true;
            }
        }
        return false;
    }

    public void printEvenIndexElements() {
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                System.out.println("a[" + i + "] = " + array[i]);
            }
        }
    }

    public void printArray() {
        System.out.print("Array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // Геттер для доступа к массиву (если нужно)
    public long[] getArray() {
        return array;
    }
}
