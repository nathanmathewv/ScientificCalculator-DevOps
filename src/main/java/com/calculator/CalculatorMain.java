package com.calculator;

import javax.swing.SwingUtilities;
import java.util.Scanner;

public class CalculatorMain {
    // hello
    // hdwuhw
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     Scientific Calculator v1.0         ║");
        System.out.println("║         Choose Your Mode               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Select calculator mode:");
        System.out.println("  1. CLI Mode (Command Line Interface)");
        System.out.println("  2. GUI Mode (Graphical User Interface)");
        System.out.println();
        System.out.print("Enter your choice (1 or 2): ");
        
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("\n✗ Invalid input! Exiting.");
            choice = 3;
        }
        
        System.out.println();
        
        if (choice == 1) {
            System.out.println("Starting CLI Mode...\n");
            CalculatorCLI cli = new CalculatorCLI();
            cli.run();
            scanner.close();
        } else if (choice == 2) {
            System.out.println("Starting GUI Mode...\n");
            scanner.close();
            SwingUtilities.invokeLater(() -> {
                CalculatorGUI calculator = new CalculatorGUI();
                calculator.setVisible(true);
            });
        } else if(choice == 3){
            // Exit on invalid input
            System.out.println("Exiting application.");
            scanner.close();
        } else {
            System.out.println("✗ Invalid choice! Please run again and select 1 or 2.");
            scanner.close();
        }
    }
}
