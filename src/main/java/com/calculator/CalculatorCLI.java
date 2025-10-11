package com.calculator;

import java.util.Scanner;

public class CalculatorCLI {
    
    private Calculator calculator;
    private Scanner scanner;
    
    public CalculatorCLI() {
        calculator = new Calculator();
        scanner = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Scientific Calculator - CLI Mode     ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            if (choice == 0) {
                running = false;
                System.out.println("\nThank you for using Scientific Calculator!");
            } else {
                processChoice(choice);
            }
        }
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         SELECT OPERATION            │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  Basic Operations:                  │");
        System.out.println("│   1. Addition (+)                   │");
        System.out.println("│   2. Subtraction (-)                │");
        System.out.println("│   3. Multiplication (×)             │");
        System.out.println("│   4. Division (÷)                   │");
        System.out.println("│                                     │");
        System.out.println("│  Scientific Functions:              │");
        System.out.println("│   5. Square Root (√)                │");
        System.out.println("│   6. Power (x^y)                    │");
        System.out.println("│   7. Factorial (n!)                 │");
        System.out.println("│   8. Natural Logarithm (ln)         │");
        System.out.println("│   9. Common Logarithm (log)         │");
        System.out.println("│  10. Exponential (e^x)              │");
        System.out.println("│                                     │");
        System.out.println("│   0. Exit                           │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("\nEnter your choice: ");
    }
    
    private int getChoice() {
        try {
            int choice = scanner.nextInt();
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Consume the newline if available
            }
            return choice;
        } catch (Exception e) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            return -1;
        }
    }
    
    private double getNumber(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextDouble();
        } catch (Exception e) {
            scanner.nextLine();
            throw new IllegalArgumentException("Invalid number format");
        }
    }
    
    private int getInteger(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            throw new IllegalArgumentException("Invalid integer format");
        }
    }
    
    private void processChoice(int choice) {
        try {
            double result = 0;
            long factorialResult = 0;
            boolean isFactorial = false;
            
            switch (choice) {
                case 1:
                    double a1 = getNumber("Enter first number: ");
                    double b1 = getNumber("Enter second number: ");
                    result = calculator.add(a1, b1);
                    System.out.println("\n✓ Result: " + a1 + " + " + b1 + " = " + result);
                    break;
                    
                case 2:
                    double a2 = getNumber("Enter first number: ");
                    double b2 = getNumber("Enter second number: ");
                    result = calculator.subtract(a2, b2);
                    System.out.println("\n✓ Result: " + a2 + " - " + b2 + " = " + result);
                    break;
                    
                case 3:
                    double a3 = getNumber("Enter first number: ");
                    double b3 = getNumber("Enter second number: ");
                    result = calculator.multiply(a3, b3);
                    System.out.println("\n✓ Result: " + a3 + " × " + b3 + " = " + result);
                    break;
                    
                case 4:
                    double a4 = getNumber("Enter numerator: ");
                    double b4 = getNumber("Enter denominator: ");
                    result = calculator.divide(a4, b4);
                    System.out.println("\n✓ Result: " + a4 + " ÷ " + b4 + " = " + result);
                    break;
                    
                case 5:
                    double x = getNumber("Enter number: ");
                    result = calculator.sqrt(x);
                    System.out.println("\n✓ Result: √" + x + " = " + result);
                    break;
                    
                case 6:
                    double base = getNumber("Enter base: ");
                    double exponent = getNumber("Enter exponent: ");
                    result = calculator.power(base, exponent);
                    System.out.println("\n✓ Result: " + base + "^" + exponent + " = " + result);
                    break;
                    
                case 7:
                    int n = getInteger("Enter non-negative integer: ");
                    factorialResult = calculator.factorial(n);
                    isFactorial = true;
                    System.out.println("\n✓ Result: " + n + "! = " + factorialResult);
                    break;
                    
                case 8:
                    double lnX = getNumber("Enter positive number: ");
                    result = calculator.ln(lnX);
                    System.out.println("\n✓ Result: ln(" + lnX + ") = " + result);
                    break;
                    
                case 9:
                    double logX = getNumber("Enter positive number: ");
                    result = calculator.log(logX);
                    System.out.println("\n✓ Result: log(" + logX + ") = " + result);
                    break;
                    
                case 10:
                    double expX = getNumber("Enter exponent: ");
                    result = calculator.exp(expX);
                    System.out.println("\n✓ Result: e^" + expX + " = " + result);
                    break;
                    
                default:
                    System.out.println("\n✗ Invalid choice! Please select a number from 0-10.");
                    break;
            }
            
        } catch (ArithmeticException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ Error: Invalid input");
            scanner.nextLine();
        }
    }
    
    public static void main(String[] args) {
        CalculatorCLI cli = new CalculatorCLI();
        cli.run();
    }
}
