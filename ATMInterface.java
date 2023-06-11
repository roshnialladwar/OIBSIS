import java.util.Scanner;


//This class represents a bank account.
//It has private instance variables userId, pin, balance, and transactionHistory.
//The userId and pin are used for authentication purposes.
//The balance stores the current account balance.
//The transactionHistory maintains a record of all transactions made on the account.
class Account {
    private String userId;
    private String pin;
    private double balance;
    private String transactionHistory;

    
//The constructor Account initializes the account with a user ID and PIN.
//It sets the initial balance to 0 and initializes the transaction history as an empty string.
    public Account(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = "";
    }

    
//The class provides getter methods for userId and balance.
    public String getUserId() {
        return userId;
    }

    
//The validatePin method takes a PIN as an argument and compares it with the account's stored PIN.
//It returns true if the provided PIN matches the account's PIN, indicating successful authentication.
    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    
//The deposit method takes an amount as an argument and adds it to the account's balance.
//It also appends a transaction record to the transactionHistory string.
//The withdraw method takes an amount as an argument and subtracts it from the account's balance if sufficient funds are available.
//It returns true if the withdrawal is successful and false otherwise.
//Similar to deposit, it appends a transaction record to transactionHistory.
//The transfer method allows transferring funds from the current account to another account (recipient account).
//It checks if the current account has sufficient funds and deducts the transferred amount from the balance.
//It also appends a transaction record to transactionHistory for both the sender and recipient accounts.
//The recipient account is provided as a parameter to the method.
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory += "Deposited: $" + amount + "\n";
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory += "Withdrawn: $" + amount + "\n";
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(Account recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory += "Transferred: $" + amount + " to " + recipient.getUserId() + "\n";
            return true;
        } else {
            return false;
        }
    }

    
//This method returns the transaction history string, which contains a record of all transactions made on the account.
    public String getTransactionHistory() {
        return transactionHistory;
    }
}


//This class contains the main method where the ATM interface is implemented.
public class ATMInterface {

    
//The main method creates a Scanner object to read user input.
//It creates an instance of the Account class with a predefined user ID and PIN.
//It prompts the user to enter their user ID and PIN for authentication.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account account = new Account("user123", "1234");

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        
//If the entered user ID matches the predefined user ID and the entered PIN matches the account's PIN, 
//the user is considered authenticated.
//After successful authentication, a menu is displayed to the user with options:
//View Transaction History
//Withdraw
//Deposit
//Transfer
//Quit
        if (account.getUserId().equals(userId) && account.validatePin(pin)) {
            System.out.println("Login successful. Welcome, " + account.getUserId() + "!");
            System.out.println("Available Options:");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            
//For option 1 (View Transaction History), the transaction history is displayed using the getTransactionHistory method.
//For option 2 (Withdraw), the user is prompted to enter the withdrawal amount, and if sufficient funds are available, the withdrawal is made and the new balance is displayed.
//For option 3 (Deposit), the user is prompted to enter the deposit amount, and the amount is added to the account balance.
//For option 4 (Transfer), the user is prompted to enter the recipient's user ID and the transfer amount.
//A dummy recipient account is created with the provided user ID (PIN is not required for this demonstration).
//If the transfer is successful, the new balance is displayed.
//For option 5 (Quit), a farewell message is displayed, and the loop ends, exiting the program
            int choice;
            do {
                System.out.print("\nEnter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.println("\nTransaction History:");
                        System.out.println(account.getTransactionHistory());
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character

                        if (account.withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful. Remaining balance: $" + account.getBalance());
                        } else {
                            System.out.println("Insufficient funds. Withdrawal unsuccessful.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character

                        account.deposit(depositAmount);
                        System.out.println("Deposit successful. New balance: $" + account.getBalance());
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientId = scanner.nextLine();

                        System.out.print("Enter amount to transfer: $");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character

                        Account recipient = new Account(recipientId, ""); //Dummy account, PIN not required for demo
                        if (account.transfer(recipient, transferAmount)) 
                        {
                            System.out.println("Transfer successful. New balance: $" + account.getBalance());
                        } 
                        else 
                        {
                            System.out.println("Insufficient funds. Transfer unsuccessful.");
                        }
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
            while (choice != 5);
        } 
        //If the user ID or PIN entered by the user doesn't match the predefined values, an "Invalid User ID or PIN" message is displayed.
        else 
        {
            System.out.println("Invalid User ID or PIN. Access denied.");
        }
        //Finally, the Scanner object is closed to release system resources.
        scanner.close();
    }  
}
