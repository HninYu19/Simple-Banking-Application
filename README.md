🧩 Code Structure & Explanation
The code is split into two main components: a BankAccount class (core banking logic) and a SimpleBankingApp class (user interface/execution).
1. BankAccount Class (Core Banking Logic)
This class encapsulates all account-related data and operations.
Key Fields (Encapsulated)
private String accountNumber;  // Unique account identifier (private - no direct access)
private String accountHolder;  // Account holder's name (private)
private double balance;        // Current account balance (private)
Fields are private to enforce encapsulation (data protection)
Getter methods are provided to access these values (e.g., getAccountNumber())
Constructor
public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
  this.accountNumber = accountNumber;
  this.accountHolder = accountHolder;
  this.balance = initialBalance;
}
Initializes a new bank account with:
Account number (e.g., "123456789")
Account holder name (e.g., "Hnin Yu")
Initial balance (set to 0.00 in the main method)
Core Methods
deposit(double amount)
Handles fund deposits:
public void deposit(double amount) {
  if (amount <= 0) {
    throw new IllegalArgumentException("Deposit amount must be positive");
  }
  balance += amount;
  System.out.printf("Successfully deposited $%.2f.\n", amount);
}
Validates that the deposit amount is positive (throws IllegalArgumentException if not)
Adds the amount to the account balance
Prints a success message with formatted currency
withdraw(double amount)
Handles fund withdrawals (throws custom exception for insufficient funds):
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
Validates withdrawal amount is positive
Checks if the account has enough balance (throws InsufficientFundsException if not)
Subtracts the amount from the balance if valid
Declares the custom exception (must be caught in the calling code)
checkBalance()
Displays the current account balance:
public void checkBalance() {
  System.out.printf("Current balance: $%.2f\n", balance);
}
Uses printf to format the balance as currency ($0.00)
Nested Custom Exception: InsufficientFundsException
java
Run
class InsufficientFundsException extends Exception {
  public InsufficientFundsException(double currentBalance, double withdrawalAmount) {
    super(String.format("Insufficient funds. Current: $%.2f, Attempted: $%.2f", currentBalance, withdrawalAmount));
  }
}
Extends Java’s Exception class (checked exception)
Custom message shows current balance and attempted withdrawal amount
Nested inside BankAccount (logical grouping—only relevant to banking operations)
Getter Methods
java
Run
public String getAccountNumber() { return accountNumber; }
public String getAccountHolder() { return accountHolder; }
public double getBalance() { return balance; }
Provide read-only access to private account fields (encapsulation best practice)

2. SimpleBankingApp Class (User Interface/Execution)
This is the main class that runs the application and handles user interaction.
main(String[] args) Method (Entry Point)
public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  BankAccount account = new BankAccount("123456789", "Hnin Yu", 0.00); // Initial balance 0

  while (true) { // Infinite loop (exits only when user selects 4)
    // Display menu
    System.out.println("\n=== Banking Menu ===");
    System.out.println("1. Deposit");
    System.out.println("2. Withdraw");
    System.out.println("3. Check Balance");
    System.out.println("4. Exit");
    System.out.print("Enter your choice: ");

    try {
      // Parse user input and handle menu choices//
      int choice = Integer.parseInt(scanner.nextLine());

      switch (choice) {
        // Case 1: Deposit
        case 1:
          System.out.print("Enter deposit amount: $");
          double depositAmount = Double.parseDouble(scanner.nextLine());
          account.deposit(depositAmount);
          break;
        
        // Case 2: Withdraw (catches InsufficientFundsException)
        case 2:
          System.out.print("Enter withdrawal amount: $");
          double withdrawalAmount = Double.parseDouble(scanner.nextLine());
          try {
            account.withdraw(withdrawalAmount);
          } catch (BankAccount.InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
          }
          break;
        
        // Case 3: Check Balance
        case 3:
          account.checkBalance();
          break;
        
        // Case 4: Exit
        case 4:
          System.out.println("Thank you for banking with us!");
          scanner.close();
          return;
        
        // Invalid menu choice
        default:
          System.out.println("Invalid choice. Please enter 1-4.");
      }
    } catch (NumberFormatException e) {
      // Handle non-numeric input (e.g., typing "abc" instead of 1)
      System.out.println("Invalid input. Please enter a number.");
    } catch (IllegalArgumentException e) {
      // Handle negative/zero deposit/withdrawal amounts
      System.out.println("Error: " + e.getMessage());
    }
  }
}
Key Components of main():
Scanner Initialization: Reads user input from the console.
Account Creation: Initializes a bank account with:
Account number: "123456789"
Holder name: "Hnin Yu"
Initial balance: $0.00
Infinite Loop: Runs the menu until the user selects "4. Exit".
Menu System: Clear options for deposit/withdraw/balance/exit.
Input Parsing:
Converts user input to integers (menu choices) and doubles (amounts)
Catches NumberFormatException (non-numeric input)
Exception Handling:
Inner try-catch for InsufficientFundsException (withdrawals)
Outer try-catch for IllegalArgumentException (invalid amounts)
Clean Exit: Closes the scanner and exits the program when user selects "4".

🎯 Usage Guide
Step 1: Launch the App
Run java SimpleBankingApp—you’ll see the menu:
plaintext
=== Banking Menu ===
1. Deposit
2. Withdraw
3. Check Balance
4. Exit
Enter your choice: 
Step 2: Deposit Funds
Enter 1 and press Enter.
Enter a positive amount (e.g., 500):
plaintext
Enter deposit amount: $500
Successfully deposited $500.00.
If you enter a negative amount (e.g., -100):
plaintext
Error: Deposit amount must be positive
Step 3: Withdraw Funds
Enter 2 and press Enter.
Enter a valid amount (e.g., 200):
plaintext
Enter withdrawal amount: $200
Successfully withdrew $200.00.
If you enter more than your balance (e.g., 1000 with $300 balance):
plaintext
Error: Insufficient funds. Current: $300.00, Attempted: $1000.00
Step 4: Check Balance
Enter 3 and press Enter:
plaintext
Current balance: $300.00
Step 5: Exit the App
Enter 4 and press Enter:
plaintext
Thank you for banking with us!
The app closes cleanly (scanner is closed to prevent resource leaks)
