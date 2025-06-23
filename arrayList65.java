/*
Дан массив A размера N и целое число K (1 ≤ K ≤ N). Преобразовать
массив, увеличив каждый его элемент на исходное значение элемента AK .
 */
import java.util.ArrayList;
import java.util.Scanner;

public class arrayList65 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива N: ");
        int N = scanner.nextInt();

        ArrayList<Integer> list = new ArrayList<>(N);

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < N; i++) {
            list.add(scanner.nextInt());
        }

        System.out.print("Введите число K (от 1 до " + N + "): ");
        int K = scanner.nextInt();

        if (K < 1 || K > N) {
            System.out.println("Неверное значение K!");
            return;
        }

        int value = list.get(K - 1);

        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + value);
        }

        System.out.println("Преобразованный массив:");
        for (int num : list) {
            System.out.print(num + " ");
        }
    }
}
