import java.math.BigDecimal;
import java.math.RoundingMode;

public class PiecewiseFunctionCLI {

    private static final int SCALE = 1; // Один знак после запятой

    public static void main(String[] args) {
        // Проверяем количество аргументов
        if (args.length != 4) {
            System.out.println("======= task 1-7============");
            System.out.println("Condition of the task:");
            System.out.println("1. (x+5) <= 0 |and| c = 0 |for|: 1/(a*x)*b");
            System.out.println("2. (x+5) >= 10 |and| c != 0 |for|: (x*a)/x");
            System.out.println("3. Another case |for|: 10*x/(c*4)");
            System.out.println("=====================================");
            System.out.println("for Use: java PiecewiseFunctionCLI a b c x");
            System.out.println();
            System.out.println("Examles from task:");
            System.out.println("1. Var 1: java PiecewiseFunctionCLI 5 5 0 -5");
            System.out.println("2. Var 2: java PiecewiseFunctionCLI 5 5 5 5");
            System.out.println("3. Var 3: java PiecewiseFunctionCLI 5 5 5 3");
            return;
        }

        try {
            // Парсим аргументы
            BigDecimal a = new BigDecimal(args[0]);
            BigDecimal b = new BigDecimal(args[1]);
            BigDecimal c = new BigDecimal(args[2]);
            BigDecimal x = new BigDecimal(args[3]);

            System.out.println("Incomes:");
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("c = " + c);
            System.out.println("x = " + x);
            System.out.println("-----------------------------------");

            // Вычисляем
            BigDecimal result = calculate(a, b, c, x);
            BigDecimal roundedResult = result.setScale(SCALE, RoundingMode.HALF_UP);

            System.out.println("Result " + result);
            System.out.println("Result (HALF_UP, 1 symbol): " + roundedResult);

        } catch (NumberFormatException e) {
            System.out.println("Error: все параметры должны быть числами!");
            System.out.println("Example: java PiecewiseFunctionCLI 5 5 0 -5");
        } catch (ArithmeticException e) {
            System.out.println("Math error: " + e.getMessage());
        }
    }

    private static BigDecimal calculate(BigDecimal a, BigDecimal b,
                                        BigDecimal c, BigDecimal x) {
        BigDecimal xPlus5 = x.add(new BigDecimal("5"));

        // Определяем, какая формула применяется
        if (xPlus5.compareTo(BigDecimal.ZERO) <= 0 &&
                c.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Formula 1: 1/(a*x)*b");
            return calculateFormula1(a, b, x);

        } else if (xPlus5.compareTo(new BigDecimal("10")) >= 0 &&
                c.compareTo(BigDecimal.ZERO) != 0) {
            System.out.println("Formula 2: (x*a)/x");
            return calculateFormula2(a, x);

        } else {
            System.out.println("Formula 3: 10*x/(c*4)");
            return calculateFormula3(c, x);
        }
    }

    private static BigDecimal calculateFormula1(BigDecimal a, BigDecimal b, BigDecimal x) {
        // 1/(a*x)*b
        BigDecimal denominator = a.multiply(x);
        if (denominator.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Деление на ноль: a*x = 0");
        }
        return BigDecimal.ONE
                .divide(denominator, 30, RoundingMode.HALF_UP)
                .multiply(b);
    }

    private static BigDecimal calculateFormula2(BigDecimal a, BigDecimal x) {
        // (x*a)/x
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Деление на ноль: x = 0");
        }
        return x.multiply(a)
                .divide(x, 30, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateFormula3(BigDecimal c, BigDecimal x) {
        // 10*x/(c*4)
        BigDecimal denominator = c.multiply(new BigDecimal("4"));
        if (denominator.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Деление на ноль: c*4 = 0");
        }
        return new BigDecimal("10").multiply(x)
                .divide(denominator, 30, RoundingMode.HALF_UP);
    }
}