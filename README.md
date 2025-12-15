# Banking Service - Technical Test

This repository contains the implementation of the **Banking Service** technical test.
The goal is to implement a simple bank account system with the following capabilities:
- Deposit money
- Withdraw money
- Print a bank statement with transaction history

## 🚀 Features

- **Deposit & Withdrawal**: Validation of inputs and balance checks.
- **Statement Printing**: Displays transactions in reverse chronological order (LIFO).
- **Date Handling**: Usage of a `IDateProvider` pattern to handle dates testability.
- **Clean Architecture**: Separation of Domain Model, Services, and Contracts.

## 🛠 Technical Choices & Architecture

### 1. Handling the "Date" Challenge
The requirements specified an acceptance test with **historical dates** (e.g., *January 10th, 2012*). 
Using `LocalDate.now()` inside the domain logic would make it impossible to reproduce this scenario or test the system deterministically.

**Solution:** 
I introduced an `IDateProvider` interface.
- In **Production**: It returns the system date (`LocalDate.now()`).
- In **Tests/Main Scenario**: We inject a Mock provider to simulate the specific dates requested in the test.

### 2. Architecture
The project follows a simplified **Domain-Driven Design (DDD)** approach:
- `domain.model`: Contains the `Account` and `Transaction` logic (Entities).
- `domain.service`: Contains `AccountService` which orchestrates operations.
- `domain.servicecontract`: Interfaces (`IAccountService`, `IDateProvider`) defining the boundaries.

### 3. Constraints Respected
- **Strict Interface**: The class `Account` / `AccountService` logic adheres to the signatures requested in the instructions.
- **Data Types**: `int` is used for money amounts as requested (though `BigDecimal` would be preferred in a real-world scenario).
- **No Database**: Data is stored in memory using `ArrayList`.

## 📦 Project Structure

```text
src/main/java/ma/skypay/bankingservice
├── Main.java                  # Acceptance Test Scenario (The "2012" simulation)
└── domain
    ├── enumeration            # Enums (TransactionType)
    ├── exception              # Custom Business Exceptions
    ├── model                  # Business Objects (Account, Transaction)
    ├── service                # Business Logic Implementation
    └── servicecontract        # Interfaces (Ports)
