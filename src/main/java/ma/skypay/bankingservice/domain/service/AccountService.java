package ma.skypay.bankingservice.domain.service;

import ma.skypay.bankingservice.domain.enumeration.TransactionType;
import ma.skypay.bankingservice.domain.model.Account;
import ma.skypay.bankingservice.domain.model.Transaction;
import ma.skypay.bankingservice.domain.servicecontract.IAccountService;
import ma.skypay.bankingservice.domain.servicecontract.IDateProvider;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountService implements IAccountService {

    private final Account account;
    private final IDateProvider dateProvider; // Dépendance ajoutée


    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public AccountService(Account account, IDateProvider dateProvider) {
        this.account = account;
        this.dateProvider = dateProvider;
    }

    @Override
    public void deposit(int amount) {
        account.deposit(amount, dateProvider.today());
    }

    @Override
    public void withdraw(int amount) {

        account.withdraw(amount, dateProvider.today());
    }

    @Override
    public void printStatement() {
        System.out.println("Date      || Amount  || Balance");

        List<Transaction> transactions = account.getTransactions();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            int signedAmount = transaction.getType() == TransactionType.WITHDRAWAL
                    ? -transaction.getAmount()
                    : transaction.getAmount();

            String formattedDate = transaction.getDate().format(DATE_FORMATTER);

            System.out.println(
                    formattedDate
                            + " || "
                            + signedAmount
                            + " || "
                            + transaction.getCurrentBalance()
            );
        }
    }
}