package Atm;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class OptionMenu extends Account {

    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    HashMap<Integer, Integer> customerData = new HashMap<>();
    HashMap<Integer, Integer> technicianData = new HashMap<>();

    public void getLogin() {
        int x = 1;
        do {
            try {
                // Dummy credentials
                customerData.put(123456, 654321);
                customerData.put(123456789, 987654321);
                technicianData.put(999999, 1111);

                System.out.println("Welcome to ATM");
                System.out.println("Are you a:");
                System.out.println("1 - Customer");
                System.out.println("2 - Technician");
                System.out.println("3 - Exit");
                System.out.print("Enter your choice: ");
                int userType = menuInput.nextInt();

                switch (userType) {
                case 1 -> {
                    handleCustomerLogin();
                }
                case 2 -> {
                    handleTechnicianLogin();
                }
                case 3 -> {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    x = 0; // Exit loop
                }
                default -> System.out.println("Invalid choice. Please enter 1, 2, or 3.\n");
            } 
                
            } catch (Exception e) {
                System.out.println("\nInvalid input. Numbers only.\n" + e);
                menuInput.nextLine(); // clear buffer
            }
        } while (x == 1);
    }

    private void handleCustomerLogin() {
        System.out.print("Enter your Customer Number: ");
        setCustomerNumber(menuInput.nextInt());

        System.out.print("Enter your PIN Number: ");
        setPinNumber(menuInput.nextInt());

        int cn = getCustomerNumber();
        int pn = getPinNumber();
        if (customerData.containsKey(cn) && customerData.get(cn) == pn) {
            getAccountType();
        } else {
            System.out.println("\nWrong Customer Number or PIN\n");
        }
    }

    private void handleTechnicianLogin() {
        System.out.print("Enter your Technician Number: ");
        int techNum = menuInput.nextInt();

        System.out.print("Enter your PIN Number: ");
        int pinNum = menuInput.nextInt();

        if (technicianData.containsKey(techNum) && technicianData.get(techNum) == pinNum) {
            getTechnicianMenu();
        } else {
            System.out.println("\nInvalid Technician Number or PIN\n");
        }
    }

    public void getAccountType() {
        System.out.println("\nSelect Account Type you want to Access");
        System.out.println("1 - Checking Account");
        System.out.println("2 - Savings Account");
        System.out.println("3 - change Mobile Number");
        System.out.println("4 - Exit");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 -> getChecking();
            case 2 -> getSaving();
            case 3 -> {
                changeMobileNumber();
                getAccountType(); // return to menu
            }
            case 4 -> System.out.println("Thank you for using ATM, BYE\n");
            default -> {
                System.out.println("\nInvalid Choice\n");
                getAccountType();
            }
        }
    }

    public void getTechnicianMenu() {
        System.out.println("\nTechnician Menu");
        System.out.println("1 - Maintenance");
        System.out.println("2 - Repair");
        System.out.println("3 - Exit");
        System.out.print("Choice: ");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 -> System.out.println("Hardware tool is unlocked and grab it.");
            case 2 -> System.out.println("Contact bank for further process.");
            case 3 -> System.out.println("Exiting Technician Menu.");
            default -> {
                System.out.println("\nInvalid Choice\n");
                getTechnicianMenu();
            }
        }
    }

    public void getChecking() {
        System.out.println("\nChecking Account");
        System.out.println("1 - View Balance");
        System.out.println("2 - Withdraw Money");
        System.out.println("3 - Deposit Funds");
        System.out.println("4 - Exit");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 -> {
                System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()));
                getAccountType();
            }
            case 2 -> {
                getCheckingWithdrawInput();
                getAccountType();
            }
            case 3 -> {
                getCheckingDepositInput();
                getAccountType();
            }
            case 4 -> System.out.println("Thank you for using ATM, Bye");
            default -> {
                System.out.println("\nInvalid Choice\n");
                getChecking();
            }
        }
    }

    public void getSaving() {
        System.out.println("\nSaving Account");
        System.out.println("1 - View Balance");
        System.out.println("2 - Withdraw Money");
        System.out.println("3 - Deposit Funds");
        System.out.println("4 - Exit");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 -> {
                System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
                getAccountType();
            }
            case 2 -> {
                getSavingWithdrawInput();
                getAccountType();
            }
            case 3 -> {
                getSavingDepositInput();
                getAccountType();
            }
            case 4 -> System.out.println("Thank you for using ATM, Bye");
            default -> {
                System.out.println("\nInvalid Choice\n");
                getSaving();
            }
        }
    }
}
