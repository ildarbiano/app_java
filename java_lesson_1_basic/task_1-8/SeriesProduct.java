import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SeriesProduct {
    public static void main(String[] args) {
        try {
            // Устанавливаем кодировку консоли
            //System.setOut(new PrintStream(System.out, true, "UTF-8"));
            // или для Windows
            System.setOut(new PrintStream(System.out, true, "CP866"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int n = 11;
        BigDecimal product = calculateProduct(n);

        // Округляем результат до 5 знаков после запятой для наглядности
        product = product.setScale(5, RoundingMode.HALF_UP);
        System.out.println("The product of the first " + n + " members of the series (without each 3-d) = " + product);
    }

    public static BigDecimal calculateProduct(int n) {
        if (n <= 0) {
            return BigDecimal.ONE;
        }

        BigDecimal product = BigDecimal.ONE;

        for (int k = 1; k <= n; k++) {
            // Пропускаем каждый 3-й член (k=3,6,9,...)
            if (k % 3 == 0) {
                System.out.println("k = " + k + " - skipped (each 3-d member)");
                continue;
            }

            // Вычисляем k-й член ряда: (k+1)/k
            BigDecimal numerator = new BigDecimal(k + 1);
            BigDecimal denominator = new BigDecimal(k);

            // Деление с точностью и округлением
            BigDecimal term = numerator.divide(denominator, 20, RoundingMode.HALF_UP);

            // Умножаем на текущее произведение
            product = product.multiply(term);

            System.out.println("k = " + k + ", member = " + term.setScale(10, RoundingMode.HALF_UP)
                    + ", composition = " + product.setScale(10, RoundingMode.HALF_UP));
        }

        return product;
    }
}