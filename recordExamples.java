
/*
 Покажите пример использования обобщенного (Generic) класса Товар
Свойства Артикул (м.б. число и строка), цена (Integer и Double)
А можно это реализовать с помощью record ? Тогда да
*/

public class recordExamples {
    public static void main(String[] args) {
        Product<String, Double> product1 = new Product<>("A1B2C3", 99.99);
        System.out.println("Товар 1: " + product1);

        Product<Integer, Integer> product2 = new Product<>(12345, 100);
        System.out.println("Товар 2: " + product2);

        Product<String, Integer> product3 = new Product<>("XYZ789", 50);
        System.out.println("Товар 3: " + product3);
    }
}

record Product<T, U>(T article, U price) {}