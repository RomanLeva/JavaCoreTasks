package group.concurrentbank_task;

import java.math.BigDecimal;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentBank {
    private final CopyOnWriteArrayList<BankAccount> bankAccounts = new CopyOnWriteArrayList<>();

    public BankAccount createAccount(BigDecimal initialBalance) {
        BankAccount bankAccount = new BankAccount(bankAccounts.size(), initialBalance);
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public void transfer(int accountFromId, int accountToId, BigDecimal amountToTransfer) {
        BankAccount fromAccount = bankAccounts.get(accountFromId);
        BankAccount toAccount = bankAccounts.get(accountToId);

        if (accountFromId < accountToId) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    fromAccount.withdraw(amountToTransfer);
                    toAccount.deposit(amountToTransfer);
                }
            }
        }
        else {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    toAccount.deposit(amountToTransfer);
                    fromAccount.withdraw(amountToTransfer);
                }
            }
        }
    }

    public BigDecimal getTotalBalance() {
        return bankAccounts.stream().map(BankAccount::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
