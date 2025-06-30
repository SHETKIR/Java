/*Создайте коллекцию accounts класса Account (поля name String, accaount double)
Заполните ее именами и состояниями счетов (можн случайным образом через Faker)
Запустите 5 потокм с методом изменеия счета клиента (параметры - имя, сумма - м.б. отрицательной)
Поскольку ту налицо race condition (нельзя разрешить нескольким потокам разрешить одновременно менять счет клиента)
Сделайте синхронизация поток с помощью механизма монитора */

import java.util.HashMap;
import java.util.Map;

public class Account {
    String name;
    double balance;

    Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        
        accounts.put("Иван", new Account("Иван", 1000));
        accounts.put("Петр", new Account("Петр", 600));
        accounts.put("Сергей", new Account("Сергей", 12000));
        accounts.put("Андрей", new Account("Андрей", 400));

        
    }
}
