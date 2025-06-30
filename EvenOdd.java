/*Введите размер массива (коллекции).
Заполните ее случайными числами.
Напишите функциональный интерфейс для нахождения среднего арифметического значения числе, стоящих на определенных позициях:
нечетных, четных, кратным 3, 4 и т.п. - цифру тоже спрашиваем у пользователя.
Использовать ссылку через :: */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;

public class EvenOdd {
    public static void main(String66[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println("Массив: " + list);

        System.out.print("Введите число для выбора позиций (например, 2, 3): ");
        int divisor = scanner.nextInt();

        BiFunction<List<Integer>, Integer, Double> avgFunction = EvenOdd::averageAtPositionsDivisibleBy;

        double average = avgFunction.apply(list, divisor);
        if (average != -1) {
            System.out.printf("Среднее арифметическое: %.2f\n", average);
        } else {
            System.out.println("Нет подходящих позиций.");
        }
    }

    public static double averageAtPositionsDivisibleBy(List<Integer> list, int divisor) {
        return list.stream()
                .filter(n -> list.indexOf(n) % divisor == 0)
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(-1);
    }

    
}