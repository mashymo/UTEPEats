/* Mashiyach Momoh
 [CS1101] Comprehensive Lab 1
 This work is to be done individually. It is not permitted to
 share, reproduce, or alter any part of this assignment for any
 purpose. Students are not permitted from sharing code, uploading
 this assignment online in any form, or viewing/receiving/
 modifying code written from anyone else. This assignment is part.
 of an academic course at The University of Texas at El Paso and
 a grade will be assigned for the work produced individually by
 the student.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CL1_Momoh {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sScanner = new Scanner(System.in); // scanner for string
        Scanner iScanner = new Scanner(System.in); // scanner for int

        // read foodMenu from file
        String source = "food.txt";
        File dataFile = new File(source);
        Scanner fileScanner = new Scanner(dataFile);

        double runningTotal = 0.0; // variables for cart set to 0
        int numItems = 0;

        System.out.println("Welcome to UTEP Eats!");

        // display main menu until user exits
        int menuChoice = 0;
        while (menuChoice != 5) {
            System.out.println("--------------Menu-------------");
            System.out.println("Please select an option (1-5): ");
            System.out.println("1. Add food to bag");
            System.out.println("2. View your order");
            System.out.println("3. Clear your order");
            System.out.println("4. Checkout");
            System.out.println("5. Exit UTEP Eats");
            System.out.println("-------------------------------");
            menuChoice = iScanner.nextInt();

            // main menu cases (1-5)
            switch (menuChoice) {
                case 1:
                    fileScanner = new Scanner(dataFile); // resetting the file scanner
                    while (fileScanner.hasNext()) {
                        String foodItem = fileScanner.next();
                        float foodPrice = fileScanner.nextFloat();
                        System.out.print(foodItem + " $");
                        System.out.println(foodPrice);
                    }

                    System.out.println("Please enter the name of the food you'd like to order: ");
                    String foodChoice = sScanner.nextLine();

                    boolean foodChoiceFound = false; // foodChoice has not been found in file

                    fileScanner = new Scanner(dataFile); // resetting the file scanner
                    while (fileScanner.hasNext()) {
                        String foodItem = fileScanner.next();
                        float foodPrice = fileScanner.nextFloat();
                        if (foodChoice.equalsIgnoreCase(foodItem)) { // reads file to check if in food menu

                            foodChoiceFound = true; // foodChoice has been found in file

                            System.out.println("Please enter the quantity that you'd like to order: ");
                            int foodQuantity = iScanner.nextInt();

                            numItems += foodQuantity;
                            runningTotal += foodQuantity * foodPrice; // calculate and add to subtotal
                            break;
                        }
                    }

                    if (!foodChoiceFound) {
                        System.out.println("Sorry we don't sell that item here.");
                    }
                    break;
                case 2:
                    System.out.println("--------------Cart-------------");
                    System.out.println("Number of Items: " + numItems);
                    System.out.println("Subtotal: $" + runningTotal);
                    System.out.println("-------------------------------");
                    break;
                case 3:
                    System.out.println("Are you sure you would like to clear your cart?");
                    System.out.println("Enter 'yes' to confirm or 'no' to cancel.");
                    String confirmClear = sScanner.nextLine();

                    if (confirmClear.equalsIgnoreCase("yes")) {
                        runningTotal = 0.0;
                        numItems = 0;
                        System.out.println("Your cart has been cleared.");
                    } else {
                        if (confirmClear.equalsIgnoreCase("no")) {
                            System.out.println("Cart will not be cleared. Returning to main menu...");
                        } else {
                            System.out.println("Invalid input. Returning to main menu...");
                        }
                    }
                    break;
                case 4:
                    if (numItems == 0) {
                        System.out.println("Your cart is empty. Please add items to your cart before checking out.");
                        break;
                    }

                    int checkoutOption = 0;
                    System.out.println("Please select an option to checkout (1-2): ");
                    System.out.println("1. Pickup");
                    System.out.println("2. Delivery");
                    checkoutOption = iScanner.nextInt();

                    switch (checkoutOption) { // pickup or delivery case
                        case 1: // pickup option
                            System.out.println("-----------Checkout------------");
                            System.out.println("Order Information");
                            System.out.println("Number of Items: " + numItems);

                            double tax = .0725 * runningTotal;
                            double total = tax + runningTotal;

                            System.out.println("Total: $" + total);
                            System.out.println("-------------------------------");

                            System.out.println("Please enter your 16 digit credit card number to complete your order: ");
                            String creditCard = sScanner.nextLine();
                            while (creditCard.length() != 16) {
                                System.out.println("Invalid entry. Please enter a 16 digit credit card number.");
                                creditCard = sScanner.nextLine();
                            }

                            System.out.println("Please enter your CVV: ");
                            String CVV = sScanner.nextLine();
                            while (CVV.length() != 3 && CVV.length() != 4) {
                                System.out.println("Invalid entry. Please enter a 3 digit CVV.");
                                CVV = sScanner.nextLine();
                            }

                            System.out.println("Please enter the expiration date: ");

                            int MM = 0;
                            int YY = 0;

                            System.out.println("Enter the month: ");
                            MM = iScanner.nextInt();
                            while (MM < 1 || MM > 12) {
                                System.out.println("Please enter a valid month.");
                                MM = iScanner.nextInt();
                            }

                            System.out.println("Enter the year (last 2 digits): ");
                            YY = iScanner.nextInt();
                            while (YY < 23 || YY > 28) {
                                System.out.println("Please enter a valid year (expiration date must be between 23 and 28).");
                                YY = iScanner.nextInt();
                            }

                            System.out.println("Please enter your zip code: ");
                            String zipCode = sScanner.nextLine();
                            while (zipCode.length() != 5) {
                                System.out.println("Invalid entry. Please enter a 5 digit zipcode.");
                                zipCode = sScanner.nextLine();
                            }

                            System.out.println("Your order has been placed. Thank you for choosing UTEP Eats!");

                            System.out.println("-----------Receipt------------");
                            System.out.println("Number of Items: " + numItems);
                            System.out.println("Total: $" + total);
                            System.out.println("------------------------------");
                            System.exit(0);
                            break;
                        case 2: // delivery option
                            System.out.println("NOTE: There is a $5 delivery fee.");

                            System.out.println("Would you like to add a tip?");
                            System.out.println("1. Yes    2. No");
                            String serviceTip = sScanner.nextLine();

                            if (serviceTip.equalsIgnoreCase("Yes") || serviceTip.equalsIgnoreCase("1")) {
                                double tipAmount = 0;

                                System.out.println("Please enter the percentage of the tip you'd like to give: ");
                                int tipPercent = iScanner.nextInt();

                                tipAmount = (tipPercent / 100.0);
                                runningTotal += (tipAmount * runningTotal);
                                System.out.println("A " + tipPercent + "% tip has been added to your total.");
                            }

                            System.out.println("-----------Checkout------------");
                            System.out.println("Order Information");
                            System.out.println("Number of Items: " + numItems);

                            tax = .0725 * runningTotal;
                            runningTotal += 5.0;
                            total = tax + runningTotal;

                            System.out.println("Total: $" + total);
                            System.out.println("-------------------------------");

                            System.out.println("Please enter your 16 digit credit card number to complete your order: ");
                            creditCard = sScanner.nextLine();
                            while (creditCard.length() != 16) {
                                System.out.println("Invalid entry. Please enter a 16 digit credit card number.");
                                creditCard = sScanner.nextLine();
                            }

                            System.out.println("Please enter your CVV: ");
                            CVV = sScanner.nextLine();
                            while (CVV.length() != 3 && CVV.length() != 4) {
                                System.out.println("Invalid entry. Please enter a 3 digit CVV.");
                                CVV = sScanner.nextLine();
                            }

                            System.out.println("Please enter the expiration date in this format (MM/YY): ");

                            MM = 0;
                            YY = 0;

                            System.out.println("Enter the month: ");
                            MM = iScanner.nextInt();
                            while (MM < 1 || MM > 12) {
                                System.out.println("Please enter a valid month.");
                                MM = iScanner.nextInt();
                            }

                            System.out.println("Enter the year (last 2 digits): ");
                            YY = iScanner.nextInt();
                            while (YY < 23 || YY > 28) {
                                System.out.println("Please enter a valid year (expiration date must be between 23 and 28).");
                                YY = iScanner.nextInt();
                            }

                            System.out.println("Please enter your address: ");
                            String deliveryAddress = sScanner.nextLine();

                            System.out.println("Please enter your city: ");
                            String deliveryCity = sScanner.nextLine();

                            System.out.println("Please enter your state: ");
                            String deliveryState = sScanner.nextLine();

                            System.out.println("Please enter your zip code: ");
                            zipCode = sScanner.nextLine();

                            while (zipCode.length() != 5) {
                                System.out.println("Invalid entry. Please enter a 5 digit zipcode.");
                                zipCode = sScanner.nextLine();
                            }

                            System.out.println("Your order has been placed. Thank you for choosing UTEP Eats!");

                            System.out.println("-----------Receipt------------");
                            System.out.println("Number of Items: " + numItems);
                            System.out.println("Total: $" + total);
                            System.out.println("Address: " + deliveryAddress);
                            System.out.println("-------------------------------");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");
                            break;
                    }
                case 5:
                    if (numItems == 0) {
                        System.out.println("Thank you for visiting UTEP Eats. Goodbye!");
                        System.out.println("Ending program...");
                        System.exit(0);
                        break;
                    }
                    System.out.println("Cart abandoned.");
                    System.out.println("Thank you for visiting UTEP Eats. Goodbye!");
                    System.out.println("Ending program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }
        }
    }
}