/*Дан массив размера N, все элементы которого, кроме последнего,
упорядочены по возрастанию. Сделать массив упорядоченным, переме-
стив последний элемент на новую позицию. */
    
import java.util.ArrayList;
import java.util.Collections;

public class arrayList88 {
    public static void main(String66[] args) {
        ArrayList<Integer> list = new ArrayList<>(java.util.Arrays.asList(1, 3, 5, 7, 9, 4));

        System.out.println("До: " + list);

        int last = list.get(list.size() - 1);

        list.remove(list.size() - 1);

        int index = Collections.binarySearch(list, last);
        if (index < 0) index = -index - 1;

        list.add(index, last);

        System.out.println("После: " + list);
    }
}
