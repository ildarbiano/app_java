package ru.lesson.l10;

import java.util.Random;

public class Main {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 10;
    private static final int RANDOM_BOUND = 300;

    public static void main(String[] args) {
        Random random = new Random();

        int m = getMatrixSize(args, 0, random);
        int n = getMatrixSize(args, 1, random);

        int[][] matrix = createMatrix(m, n, random);

        printMatrix(matrix);
        printMinInRows(matrix);
        printAverage(matrix);
    }

    // Определение размерности матрицы
    private static int getMatrixSize(String[] args, int index, Random random) {
        if (args.length > index) {
            return Integer.parseInt(args[index]);
        }
        return random.nextInt(MAX_SIZE - MIN_SIZE + 1) + MIN_SIZE;
    }

    // Создание и инициализация матрицы
    private static int[][] createMatrix(int m, int n, Random random) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(RANDOM_BOUND) + 1;
            }
        }
        return matrix;
    }

    // Печать матрицы
    private static void printMatrix(int[][] matrix) {
        System.out.println("Matrix (" + matrix.length + " x " + matrix[0].length + "):");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%5d", value);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Минимальный элемент в каждой строке
    private static void printMinInRows(int[][] matrix) {
        System.out.println("Minimum in each row:");
        for (int i = 0; i < matrix.length; i++) {
            int min = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
            System.out.println("Row " + (i + 1) + ": " + min);
        }
        System.out.println();
    }

    // Среднее значение элементов матрицы
    private static void printAverage(int[][] matrix) {
        long sum = 0;
        int count = 0;

        for (int[] row : matrix) {
            for (int value : row) {
                sum += value;
                count++;
            }
        }

        double average = (double) sum / count;
        System.out.printf("Average value: %.2f%n", average);
    }
}
