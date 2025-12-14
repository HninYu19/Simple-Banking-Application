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
The app closes cleanly (scanner is closed to prevent resource leaks).
