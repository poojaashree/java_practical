package com.texas.javapractical;
import java.util.Scanner;

    public class Calculator {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- Calculator Menu ---");
                System.out.println("1. Addition");
                System.out.println("2. Subtraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                if (choice == 0) {
                    System.out.println("Exiting Calculator...");
                    break;
                }

                System.out.print("Enter first number: ");
                int a = sc.nextInt();
                System.out.print("Enter second number: ");
                int b = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Result = " + (a + b));
                        break;
                    case 2:
                        System.out.println("Result = " + (a - b));
                        break;
                    case 3:
                        System.out.println("Result = " + (a * b));
                        break;
                    case 4:
                        if (b == 0) {
                            System.out.println("Error: Division by zero!");
                        } else {
                            System.out.println("Result = " + ((double) a / b));
                        }
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 0);

            sc.close();
        }
}
