package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    
    private Calculator calculator;
    private JTextField display;
    private StringBuilder currentInput;
    private double operand1;
    private String lastOperation;
    private boolean newInput;

    public CalculatorGUI() {
        calculator = new Calculator();
        currentInput = new StringBuilder("0");
        operand1 = 0;
        lastOperation = "";
        newInput = true;
        
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(400, 60));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
            "√", "x^y", "n!", "ln",
            "C", "CE", "←", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "±", "0", ".", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            
            if (label.matches("[0-9]") || label.equals(".") || label.equals("±")) {
                button.setBackground(new Color(232, 232, 232));
                button.setForeground(Color.BLACK);
            } else if (label.equals("C") || label.equals("CE") || label.equals("←")) {
                button.setBackground(new Color(255, 107, 107));
                button.setForeground(Color.BLACK);
            } else if (label.equals("+") || label.equals("-") || label.equals("×") || 
                       label.equals("÷") || label.equals("=")) {
                button.setBackground(new Color(78, 205, 196));
                button.setForeground(Color.BLACK);
            } else if (label.equals("√") || label.equals("x^y") || label.equals("n!") || label.equals("ln")) {
                button.setBackground(new Color(255, 217, 61));
                button.setForeground(Color.BLACK);
            }
            
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setContentAreaFilled(true);
            button.setFocusPainted(false);
            
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        try {
            if (command.matches("[0-9]")) {
                handleDigit(command);
            } else if (command.equals(".")) {
                handleDecimal();
            } else if (command.equals("C")) {
                clearAll();
            } else if (command.equals("CE")) {
                clearEntry();
            } else if (command.equals("←")) {
                backspace();
            } else if (command.equals("+") || command.equals("-") || 
                       command.equals("×") || command.equals("÷") || command.equals("x^y")) {
                handleOperation(command);
            } else if (command.equals("=")) {
                calculateResult();
            } else if (command.equals("√")) {
                handleSqrt();
            } else if (command.equals("n!")) {
                handleFactorial();
            } else if (command.equals("ln")) {
                handleLn();
            } else if (command.equals("±")) {
                handleSignChange();
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void handleDigit(String digit) {
        if (newInput) {
            currentInput = new StringBuilder(digit);
            newInput = false;
        } else {
            currentInput.append(digit);
        }
        display.setText(currentInput.toString());
    }

    private void handleDecimal() {
        if (newInput) {
            currentInput = new StringBuilder("0.");
            newInput = false;
        } else if (!currentInput.toString().contains(".")) {
            currentInput.append(".");
        }
        display.setText(currentInput.toString());
    }

    private void clearAll() {
        currentInput = new StringBuilder("0");
        display.setText("0");
        operand1 = 0;
        lastOperation = "";
        newInput = true;
    }

    private void clearEntry() {
        currentInput = new StringBuilder("0");
        display.setText("0");
        newInput = true;
    }

    private void backspace() {
        if (!newInput && currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            if (currentInput.length() == 0 || currentInput.toString().equals("-")) {
                currentInput = new StringBuilder("0");
            }
            display.setText(currentInput.toString());
        }
    }

    private void handleOperation(String op) {
        if (!lastOperation.isEmpty() && !newInput) {
            calculateResult();
        }
        operand1 = getCurrentValue();
        lastOperation = op;
        newInput = true;
    }

    private void calculateResult() {
        if (!lastOperation.isEmpty()) {
            double operand2 = getCurrentValue();
            double result = 0;

            switch (lastOperation) {
                case "+":
                    result = calculator.add(operand1, operand2);
                    break;
                case "-":
                    result = calculator.subtract(operand1, operand2);
                    break;
                case "×":
                    result = calculator.multiply(operand1, operand2);
                    break;
                case "÷":
                    result = calculator.divide(operand1, operand2);
                    break;
                case "x^y":
                    result = calculator.power(operand1, operand2);
                    break;
            }

            displayResult(result);
            lastOperation = "";
            operand1 = 0;
            newInput = true;
        }
    }

    private void handleSqrt() {
        double value = getCurrentValue();
        double result = calculator.sqrt(value);
        displayResult(result);
        newInput = true;
    }

    private void handleFactorial() {
        double value = getCurrentValue();
        if (value != (int) value || value < 0) {
            throw new IllegalArgumentException("Factorial only defined for non-negative integers");
        }
        long result = calculator.factorial((int) value);
        displayResult(result);
        newInput = true;
    }

    private void handleLn() {
        double value = getCurrentValue();
        double result = calculator.ln(value);
        displayResult(result);
        newInput = true;
    }

    private void handleSignChange() {
        if (!currentInput.toString().equals("0")) {
            if (currentInput.charAt(0) == '-') {
                currentInput.deleteCharAt(0);
            } else {
                currentInput.insert(0, '-');
            }
            display.setText(currentInput.toString());
        }
    }

    private double getCurrentValue() {
        try {
            return Double.parseDouble(currentInput.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void displayResult(double result) {
        String formatted = formatResult(result);
        currentInput = new StringBuilder(formatted);
        display.setText(formatted);
    }

    private void displayResult(long result) {
        String formatted = String.valueOf(result);
        currentInput = new StringBuilder(formatted);
        display.setText(formatted);
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.10f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        clearAll();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
