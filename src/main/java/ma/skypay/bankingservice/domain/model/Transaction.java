package ma.skypay.bankingservice.domain.model;

import ma.skypay.bankingservice.domain.enumeration.TransactionType;
import java.time.LocalDate;

public class Transaction {

    private final int amount;
    private final int currentBalance;
    private final LocalDate date;
    private final TransactionType type;

    public Transaction(int amount, int currentBalance, LocalDate date, TransactionType type) {
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.date = date;
        this.type = type;
    }

    public int getAmount() { return amount; }
    public int getCurrentBalance() { return currentBalance; }
    public LocalDate getDate() { return date; }
    public TransactionType getType() { return type; }
}