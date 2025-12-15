package ma.skypay.bankingservice.domain.servicecontract;

public interface IAccountService {

    void deposit(int amount);

    void withdraw(int amount);

    void printStatement();
}
