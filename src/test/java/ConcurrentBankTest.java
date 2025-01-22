import group.concurrentbank_task.BankAccount;
import group.concurrentbank_task.ConcurrentBank;
import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrentBankTest {
    @Test
    public void testConcurrentBank() {
        BigDecimal balanceForAccount1 = new BigDecimal("1000.00");
        BigDecimal balanceForAccount2 = new BigDecimal("500.00");

        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(balanceForAccount1);
        BankAccount account2 = bank.createAccount(balanceForAccount2);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1.getAccountId(), account2.getAccountId(), BigDecimal.valueOf(200)));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2.getAccountId(), account1.getAccountId(), BigDecimal.valueOf(100)));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());

        // Проверка
        BigDecimal totalBalance = balanceForAccount1.add(balanceForAccount2);
        assertEquals(totalBalance, bank.getTotalBalance());
    }
}
