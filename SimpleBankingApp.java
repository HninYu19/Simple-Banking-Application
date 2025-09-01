import java.util.Scanner;

class BankAccount {
  private String accountNumber;
  private String accountHolder;
  private double balance;

  public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
    this.accountNumber = accountNumber;
    this.accountHolder = accountHolder;
    this.balance = initialBalance;
  }

  public void deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive");
    }
    balance += amount;
    System.out.printf("Successfully deposited $%.2f.\n", amount);
  }

  public void withdraw(double amount) throws InsufficientFundsException {
    if (amount <= 0) {
      throw new IllegalArgumentException("Withdrawal amount must be positive");
    }
    if (amount > balance) {
      throw new InsufficientFundsException(balance, amount);
    }
    balance -= amount;
    System.out.printf("Successfully withdrew $%.2f.\n",amount);
  }

  public void checkBalance() {
    System.out.printf("Current balance: $%.2f\n", balance);
  }

  class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double currentBalance, double withdrawalAmount) {
      super(String.format("Insufficient funds. Current: $%.2f, Attempted: $%.2f", currentBalance, withdrawalAmount));
    }
  }

  public String getAccountNumber() { return accountNumber; }
  public String getAccountHolder() { return accountHolder; }
  public double getBalance() { return balance; }
}

public class SimpleBankingApp {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    BankAccount account = new BankAccount("123456789", "Hnin Yu", 0.00); // Initial balance set to 0

    while (true) {
      System.out.println("\n=== Banking Menu ===");
      System.out.println("1. Deposit");
      System.out.println("2. Withdraw");
      System.out.println("3. Check Balance");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");

      try {
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
        case 1:
          System.out.print("Enter deposit amount: $");
          double depositAmount = Double.parseDouble(scanner.nextLine());
          account.deposit(depositAmount);
          break;
        case 2:
          System.out.print("Enter withdrawal amount: $");
          double withdrawalAmount = Double.parseDouble(scanner.nextLine());
          try {
            account.withdraw(withdrawalAmount);
          } catch (BankAccount.InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
          }
          break;
        case 3:
          account.checkBalance();
          break;
        case 4:
          System.out.println("Thank you for banking with us!");
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice. Please enter 1-4.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}