package ma.skypay.bankingservice.domain.model;

import ma.skypay.bankingservice.domain.enumeration.TransactionType;
import ma.skypay.bankingservice.domain.exception.BusinessException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private final String accountNumber;
    private int balance;
    private final List<Transaction> transactions;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }


    public void deposit(int amount, LocalDate transactionDate) {
        if (amount <= 0) {
            throw new BusinessException("Invalid deposit amount");
        }

        balance += amount;
        transactions.add(new Transaction(
                amount,
                balance,
                transactionDate,
                TransactionType.DEPOSIT
        ));
    }

    public void withdraw(int amount, LocalDate transactionDate) {
        if (amount <= 0) {
            throw new BusinessException("Invalid withdrawal amount");
        }

        if (balance < amount) {
            throw new BusinessException("Insufficient balance");
        }

        balance -= amount;
        transactions.add(new Transaction(
                amount,
                balance,
                transactionDate,
                TransactionType.WITHDRAWAL
        ));
    }
}