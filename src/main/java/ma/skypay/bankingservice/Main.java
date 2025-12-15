package ma.skypay.bankingservice;

import ma.skypay.bankingservice.domain.model.Account;
import ma.skypay.bankingservice.domain.service.AccountService;
import ma.skypay.bankingservice.domain.servicecontract.IAccountService;
import ma.skypay.bankingservice.domain.servicecontract.IDateProvider;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {


        IDateProvider mockDateProvider = new IDateProvider() {
            private int callCount = 0;

            @Override
            public LocalDate today() {
                callCount++;

                if (callCount == 1) return LocalDate.of(2012, 1, 10);
                if (callCount == 2) return LocalDate.of(2012, 1, 13);
                if (callCount == 3) return LocalDate.of(2012, 1, 14);
                return LocalDate.now();
            }
        };

        Account account = new Account("123-456");

        IAccountService bankingService = new AccountService(account, mockDateProvider);

        System.out.println("--- Test---");
        bankingService.deposit(1000);  // date : 10/01/2012
        bankingService.deposit(2000);  // date : 13/01/2012
        bankingService.withdraw(500);  // date : 14/01/2012

        bankingService.printStatement();
    }
}