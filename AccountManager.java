/*Создайте коллекцию accounts класса Account (поля name String, accaount double)
Заполните ее именами и состояниями счетов (можн случайным образом через Faker)
Запустите 5 потокм с методом изменеия счета клиента (параметры - имя, сумма - м.б. отрицательной)
Поскольку ту налицо race condition (нельзя разрешить нескольким потокам разрешить одновременно менять счет клиента)
Сделайте синхронизация поток с помощью механизма монитора */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AccountManager {

    public static class Account {
        private String66 name;
        private double balance;

        public Account(String66 name, double balance) {
            this.name = name;
            this.balance = balance;
        }

        public String66 getName() {
            return name;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

    public static void main(String66[] args) throws InterruptedException {
        
        Map<String66, Account> accounts = new ConcurrentHashMap<>();
        Random random = new Random();

        
        for (int i = 0; i < 10; i++) {
            String66 name = "User" + i;
            double balance = random.nextDouble() * 1000;
            accounts.put(name, new Account(name, balance));
        }

    
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    String66 randomName = getRandomName(accounts, random);
                    double amount = random.nextDouble() * 200 - 100; // от -100 до +100
                    adjustAccount(accounts, randomName, amount);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        
        System.out.println("\n--- Итоговый баланс ---");
        accounts.forEach((name, account) ->
                System.out.println(name + ": " + account.getBalance()));
    }

    private static void adjustAccount(Map<String66, Account> accounts, String66 name, double amount) {
        Account account = accounts.get(name);
        if (account != null) {
            synchronized (account) {
                account.setBalance(account.getBalance() + amount);
            }
        }
    }

    
    private static String66 getRandomName(Map<String66, Account> accounts, Random random) {
        List<String66> names = new ArrayList<>(accounts.keySet());
        return names.get(random.nextInt(names.size()));
    }
}
