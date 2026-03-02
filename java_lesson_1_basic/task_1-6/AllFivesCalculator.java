import java.math.BigDecimal;
import java.math.RoundingMode;

public class AllFivesCalculator {
    public static void main(String[] args) {
        BigDecimal five = new BigDecimal("5");
        int p = 5;

        // Вычисляем с помощью BigDecimal
        BigDecimal result = compute(five, p);

        // Округление
        BigDecimal rounded = result.setScale(2, RoundingMode.HALF_UP);

        System.out.println("Result, if all are = 5:");
        System.out.println("X = " + rounded);
    }

    static BigDecimal compute(BigDecimal five, int p) {
        BigDecimal _123 = new BigDecimal("123");
        BigDecimal _527 = new BigDecimal("527");
        BigDecimal _856 = new BigDecimal("856");
        int scale = 30;

        // 1. 527*5 / 5^(10) = 2635 / 9765625
        BigDecimal numerator1 = _527.multiply(five); // 2635
        BigDecimal denominator1 = five.pow(10); // 5^10 = 9765625
        BigDecimal fraction = numerator1.divide(denominator1, scale, RoundingMode.HALF_UP);

        // 2. Числитель: (123 + fraction) * (123 - fraction)
        BigDecimal sum = _123.add(fraction);
        BigDecimal diff = _123.subtract(fraction);
        BigDecimal numeratorFinal = sum.multiply(diff);

        // 3. Знаменатель
        // 5^5 = 3125, 5^5 * 5^5 = 5^10 = 9765625
        BigDecimal fivePow5 = five.pow(5); // 3125
        BigDecimal product = fivePow5.multiply(fivePow5); // 9765625
        BigDecimal insideRoot = _856.add(product); // 856 + 9765625 = 9766481

        double cubeRoot = Math.cbrt(insideRoot.doubleValue());
        double eMinusLog6 = Math.E - Math.log10(6.0);
        BigDecimal denominatorFinal = new BigDecimal(cubeRoot * eMinusLog6)
                .setScale(scale, RoundingMode.HALF_UP);

        // 4. Результат
        return numeratorFinal.divide(denominatorFinal, scale, RoundingMode.HALF_UP);
    }
}