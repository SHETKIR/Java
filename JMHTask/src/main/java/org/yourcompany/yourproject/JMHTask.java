package org.yourcompany.yourproject;

/**
 *
 * @author andrey
 */

/*Java С использованием обычных массивов, коллекций и Stream API
Введите n > 0, заполните случайными числами от 0 до 100.
Организовать параллельную фильтрацию и сортировку:
10. Дана матрица размера MхN. Для каждого столбца матрицы найти произведение его элементов. */

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JMHTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите n > 0: ");
        int n = scanner.nextInt();
        if (n <= 0) return;
        
        int[][] matrix = new int[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(101);
            }
        }

        List<Long> products = IntStream.range(0, n)
            .parallel()
            .mapToObj(col -> {
                long product = 1;
                for (int row = 0; row < n; row++) {
                    product *= matrix[row][col];
                }
                return product;
            })
            .collect(Collectors.toList());

        System.out.println("Произведения столбцов: " + products);
    }
}