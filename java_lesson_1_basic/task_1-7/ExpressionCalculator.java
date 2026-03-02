import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExpressionCalculator {
    public static void main(String[] args) {
        // Вариант 1: a = b = 5, c = 0, x = -5
        BigDecimal a1 = new BigDecimal("5");
        BigDecimal b1 = new BigDecimal("5");
        BigDecimal c1 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal("-5");
        BigDecimal result1 = calculateExpression(a1, b1, c1, x1);

        // Вариант 2: a = b = c = x = 5
        BigDecimal a2 = new BigDecimal("5");
        BigDecimal b2 = new BigDecimal("5");
        BigDecimal c2 = new BigDecimal("5");
        BigDecimal x2 = new BigDecimal("5");
        BigDecimal result2 = calculateExpression(a2, b2, c2, x2);

        // Вариант 3: a = b = c = 5, x = 3
        BigDecimal a3 = new BigDecimal("5");
        BigDecimal b3 = new BigDecimal("5");
        BigDecimal c3 = new BigDecimal("5");
        BigDecimal x3 = new BigDecimal("3");
        BigDecimal result3 = calculateExpression(a3, b3, c3, x3);

        // Вывод результатов в требуемом формате
        System.out.println(result1 + "; " + result2 + "; " + result3);
    }

    public static BigDecimal calculateExpression(BigDecimal a, BigDecimal b,
                                                 BigDecimal c, BigDecimal x) {
        // Условия для вычисления выражения
        BigDecimal xPlus5 = x.add(new BigDecimal("5"));
        BigDecimal condition1 = new BigDecimal("0");
        BigDecimal condition2 = new BigDecimal("10");

        BigDecimal result;

        // Первое условие: (x+5) <= 0 и c = 0
        if (xPlus5.compareTo(condition1) <= 0 && c.compareTo(BigDecimal.ZERO) == 0) {
            // 1/(a*x)*b
            BigDecimal denominator = a.multiply(x);
            if (denominator.compareTo(BigDecimal.ZERO) == 0) {
                throw new ArithmeticException("Деление на ноль в первом выражении");
            }
            result = BigDecimal.ONE.divide(denominator, 10, RoundingMode.HALF_UP)
                    .multiply(b);
        }
        // Второе условие: (x+5) >= 10 и c != 0
        else if (xPlus5.compareTo(condition2) >= 0 && c.compareTo(BigDecimal.ZERO) != 0) {
            // (x*a)/x
            if (x.compareTo(BigDecimal.ZERO) == 0) {
                throw new ArithmeticException("Деление на ноль во втором выражении");
            }
            result = x.multiply(a).divide(x, 10, RoundingMode.HALF_UP);
        }
        // В остальных случаях
        else {
            // 10*x/(c*4)
            if (c.multiply(new BigDecimal("4")).compareTo(BigDecimal.ZERO) == 0) {
                throw new ArithmeticException("Деление на ноль в третьем выражении");
            }
            result = new BigDecimal("10").multiply(x)
                    .divide(c.multiply(new BigDecimal("4")), 10, RoundingMode.HALF_UP);
        }

        // Округление до одного знака после запятой
        return result.setScale(1, RoundingMode.HALF_UP);
    }
}