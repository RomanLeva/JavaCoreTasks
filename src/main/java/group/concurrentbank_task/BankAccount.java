package group.concurrentbank_task;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {
    private BigDecimal balance;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public BankAccount(BigDecimal initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(BigDecimal amount) {
        Lock writeLock = rwLock.writeLock();
        writeLock.lock();
        try {
            balance = balance.add(amount);
            System.out.println("Deposited: " + amount + ", New Balance: " + balance);
        } finally {
            writeLock.unlock();
        }
    }

    public void withdraw(BigDecimal amount) {
        Lock writeLock = rwLock.writeLock();
        writeLock.lock();
        try {
            if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(balance) <= 0) {
                balance = balance.subtract(amount);
                System.out.println("Withdrew: " + amount + ", New Balance: " + balance);
            } else {
                System.out.println("Insufficient funds");
            }
        } finally {
            writeLock.unlock();
        }
    }

    public BigDecimal getBalance() {
        Lock readLock = rwLock.readLock();
        readLock.lock();
        try {
            return balance;
        } finally {
            readLock.unlock();
        }
    }
}
