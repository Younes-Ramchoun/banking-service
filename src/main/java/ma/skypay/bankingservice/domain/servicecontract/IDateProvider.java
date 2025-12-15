package ma.skypay.bankingservice.domain.servicecontract;

import java.time.LocalDate;

public interface IDateProvider {
    LocalDate today();
}