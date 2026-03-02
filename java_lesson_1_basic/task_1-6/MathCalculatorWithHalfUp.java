import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MathCalculatorWithHalfUp {

    // Константы
    private static final BigDecimal BIG_123 = new BigDecimal("123");
    private static final BigDecimal BIG_527 = new BigDecimal("527");
    private static final BigDecimal BIG_856 = new BigDecimal("856");

    public static void main(String[] args) {
        // Проверяем количество аргументов
        if (args.length != 7) {
            System.out.println("Use: java MathCalculatorWithHalfUp a b c d m n p");
            System.out.println("Пример: java MathCalculatorWithHalfUp 2.0 3.0 1.5 2.5 2 3 1");
            System.out.println("\nОкругление: HALF_UP (к ближайшему, при равенстве - вверх)");
            System.out.println("Точность: 2 знака после запятой");
            return;
        }

        try {
            // Парсим входные параметры
            BigDecimal a = new BigDecimal(args[0]);
            BigDecimal b = new BigDecimal(args[1]);
            BigDecimal c = new BigDecimal(args[2]);
            BigDecimal d = new BigDecimal(args[3]);
            int m = Integer.parseInt(args[4]);
            int n = Integer.parseInt(args[5]);
            int p = Integer.parseInt(args[6]);

            // Вычисляем выражение
            BigDecimal result = calculateExpression(a, b, c, d, m, n, p);

            // Округляем итоговый результат до 2 знаков после запятой
            BigDecimal roundedResult = result.setScale(2, RoundingMode.HALF_UP);

            // Выводим результаты
            System.out.println("Income data:");
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("c = " + c);
            System.out.println("d = " + d);
            System.out.println("m = " + m);
            System.out.println("n = " + n);
            System.out.println("p = " + p);
            System.out.println("-----------------------------------");
            System.out.println("Smart result: " + result);
            System.out.println("Result with  (HALF_UP, 2 simbol): " + roundedResult);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: все параметры должны быть числами");
            System.out.println("a, b, c, d - десятичные числа (например: 1.5 или 2)");
            System.out.println("m, n, p - целые числа (например: 2)");
        } catch (ArithmeticException e) {
            System.out.println("Математическая ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при вычислении: " + e.getMessage());
        }
    }

    private static BigDecimal calculateExpression(
            BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d,
            int m, int n, int p) {

        // Устанавливаем точность вычислений с округлением HALF_UP
        MathContext mc = new MathContext(50, RoundingMode.HALF_UP);

        // 1. Вычисляем: 527 * a / b^(2p)
        // b^(2p)
        BigDecimal bPower2p = b.pow(2 * p, mc);

        // Проверка деления на ноль
        if (bPower2p.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Деление на ноль: b^(2p) = 0");
        }

        // 527 * a
        BigDecimal _527a = BIG_527.multiply(a, mc);

        // 527 * a / b^(2p) - используем деление с указанным округлением
        // Для деления используем scale = 50 и HALF_UP
        BigDecimal part1 = _527a.divide(bPower2p, 50, RoundingMode.HALF_UP);

        // 2. Вычисляем числитель: (123 + part1) * (123 - part1)
        BigDecimal sum = BIG_123.add(part1, mc);
        BigDecimal diff = BIG_123.subtract(part1, mc);
        BigDecimal numerator = sum.multiply(diff, mc);

        // 3. Вычисляем знаменатель:
        //    ∛(856 + c^n * d^m) * (e - lg6)

        // 3.1. c^n
        BigDecimal cPowerN;
        if (n == 0) {
            cPowerN = BigDecimal.ONE;
        } else {
            cPowerN = c.pow(n, mc);
        }

        // 3.2. d^m
        BigDecimal dPowerM;
        if (m == 0) {
            dPowerM = BigDecimal.ONE;
        } else {
            dPowerM = d.pow(m, mc);
        }

        // 3.3. c^n * d^m
        BigDecimal cPowerN_dPowerM = cPowerN.multiply(dPowerM, mc);

        // 3.4. 856 + c^n * d^m
        BigDecimal insideCubeRoot = BIG_856.add(cPowerN_dPowerM, mc);

        // Проверка на отрицательное число под корнем
        if (insideCubeRoot.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("Отрицательное число под кубическим корнем: " + insideCubeRoot);
        }

        // 3.5. ∛(856 + c^n * d^m) - кубический корень
        double insideDouble = insideCubeRoot.doubleValue();
        double cubeRoot = Math.cbrt(insideDouble);

        // Конвертируем обратно в BigDecimal с округлением
        BigDecimal cubeRootBig = new BigDecimal(cubeRoot, mc);

        // 3.6. e - lg6 (число Эйлера - десятичный логарифм 6)
        double euler = Math.E;
        double log6 = Math.log10(6);
        double eMinusLog6 = euler - log6;

        // Конвертируем в BigDecimal с округлением
        BigDecimal eMinusLog6Big = new BigDecimal(eMinusLog6, mc);

        // 3.7. Вторая часть знаменателя: ∛(...) * (e - lg6)
        BigDecimal denominator = cubeRootBig.multiply(eMinusLog6Big, mc);

        // Проверка деления на ноль в знаменателе
        if (denominator.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Деление на ноль: знаменатель равен 0");
        }

        // 4. Result: up / down
        // use / with scale = 50 and HALF_UP
        BigDecimal result = numerator.divide(denominator, 50, RoundingMode.HALF_UP);

        return result;
    }
}
