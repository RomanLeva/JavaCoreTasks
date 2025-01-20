package group.concurrentbank_task;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private Map<String,BankAccount> bankAccounts = new HashMap<>();
    public Lock lock = new ReentrantLock();

    public BankAccount createAccount(BigDecimal initialBalance) {
        lock.lock();
        try {
            BankAccount bankAccount = new BankAccount(initialBalance);
            bankAccounts.put("account" + bankAccounts.size() + 1, bankAccount);
            return bankAccount;
        }finally {
            lock.unlock();
        }
    }

    public void transfer(BankAccount account1, BankAccount account2, BigDecimal amountToTransfer) {
        lock.lock();
        try {
            account1.withdraw(amountToTransfer);
            account2.deposit(amountToTransfer);
        } finally {
            lock.unlock();
        }

    }

    public BigDecimal getTotalBalance() {
        return bankAccounts.values().stream().map(BankAccount::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
