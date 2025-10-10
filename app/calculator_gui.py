import tkinter as tk
from tkinter import ttk, messagebox
import math
from calculator import Calculator


class ScientificCalculatorGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Scientific Calculator")
        self.root.geometry("400x550")
        self.root.resizable(False, False)
        
        self.calc = Calculator()
        self.current_input = ""
        self.result_var = tk.StringVar()
        self.result_var.set("0")
        self.new_input = True
        self.last_operation = None
        self.operand1 = None
        
        self.setup_ui()
    
    def setup_ui(self):
        main_frame = ttk.Frame(self.root, padding="10")
        main_frame.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        display_frame = ttk.Frame(main_frame)
        display_frame.grid(row=0, column=0, columnspan=4, pady=(0, 10))
        
        display = ttk.Entry(display_frame, textvariable=self.result_var, 
                           font=('Arial', 24, 'bold'), justify=tk.RIGHT,
                           state='readonly', width=18)
        display.pack(fill=tk.BOTH, expand=True, ipady=10)
        
        button_style = {'width': 8, 'height': 2, 'font': ('Arial', 12)}
        
        buttons_row1 = [
            ('√', 1, 0), ('xʸ', 1, 1), ('n!', 1, 2), ('ln', 1, 3)
        ]
        
        buttons_row2 = [
            ('C', 2, 0), ('CE', 2, 1), ('←', 2, 2), ('÷', 2, 3)
        ]
        
        buttons_row3 = [
            ('7', 3, 0), ('8', 3, 1), ('9', 3, 2), ('×', 3, 3)
        ]
        
        buttons_row4 = [
            ('4', 4, 0), ('5', 4, 1), ('6', 4, 2), ('-', 4, 3)
        ]
        
        buttons_row5 = [
            ('1', 5, 0), ('2', 5, 1), ('3', 5, 2), ('+', 5, 3)
        ]
        
        buttons_row6 = [
            ('±', 6, 0), ('0', 6, 1), ('.', 6, 2), ('=', 6, 3)
        ]
        
        # Create all buttons
        all_buttons = (buttons_row1 + buttons_row2 + buttons_row3 + 
                      buttons_row4 + buttons_row5 + buttons_row6)
        
        for btn_data in all_buttons:
            text = btn_data[0]
            row = btn_data[1]
            col = btn_data[2]
            
            # Determine button color
            if text in ['C', 'CE', '←']:
                bg_color = '#FF6B6B'
            elif text in ['÷', '×', '-', '+', '=']:
                bg_color = '#4ECDC4'
            elif text in ['√', 'xʸ', 'n!', 'ln']:
                bg_color = '#FFD93D'
            elif text.isdigit() or text == '.' or text == '±':
                bg_color = '#E8E8E8'
            else:
                bg_color = '#DDDDDD'
            
            btn = tk.Button(main_frame, text=text, command=lambda t=text: self.button_click(t),
                           bg=bg_color, activebackground=bg_color, **button_style)
            btn.grid(row=row, column=col, padx=2, pady=2, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        for i in range(4):
            main_frame.columnconfigure(i, weight=1)
    
    def button_click(self, button):
        try:
            if button.isdigit() or button == '.':
                if self.new_input:
                    self.current_input = button
                    self.new_input = False
                else:
                    if button == '.' and '.' in self.current_input:
                        return
                    self.current_input += button
                self.result_var.set(self.current_input)
            
            elif button == 'C':
                self.clear_all()
            elif button == 'CE':
                self.clear_entry()
            elif button == '←':
                self.backspace()
            
            elif button in ['+', '-', '×', '÷']:
                self.handle_operation(button)
            
            elif button == '=':
                self.calculate_result()
            
            elif button == '√':
                self.handle_sqrt()
            elif button == 'xʸ':
                self.handle_operation('xʸ')
            elif button == 'n!':
                self.handle_factorial()
            elif button == 'ln':
                self.handle_ln()
            elif button == '±':
                self.handle_sign_change()
                
        except Exception as e:
            self.show_error(str(e))
    
    def get_current_value(self):
        if not self.current_input or self.current_input == '-':
            return 0
        return float(self.current_input)
    
    def handle_operation(self, op):
        if self.last_operation and not self.new_input:
            self.calculate_result()
        self.operand1 = self.get_current_value()
        self.last_operation = op
        self.new_input = True
    
    def calculate_result(self):
        if self.last_operation and self.operand1 is not None:
            operand2 = self.get_current_value()
            
            if self.last_operation == '+':
                result = self.calc.add(self.operand1, operand2)
            elif self.last_operation == '-':
                result = self.calc.subtract(self.operand1, operand2)
            elif self.last_operation == '×':
                result = self.calc.multiply(self.operand1, operand2)
            elif self.last_operation == '÷':
                result = self.calc.divide(self.operand1, operand2)
            elif self.last_operation == 'xʸ':
                result = self.calc.power(self.operand1, operand2)
            
            self.current_input = str(result)
            self.result_var.set(self.format_result(result))
            self.last_operation = None
            self.operand1 = None
            self.new_input = True
    
    def handle_sqrt(self):
        value = self.get_current_value()
        result = self.calc.sqrt(value)
        self.current_input = str(result)
        self.result_var.set(self.format_result(result))
        self.new_input = True
    
    def handle_factorial(self):
        value = self.get_current_value()
        result = self.calc.factorial(value)
        self.current_input = str(result)
        self.result_var.set(self.format_result(result))
        self.new_input = True
    
    def handle_ln(self):
        value = self.get_current_value()
        result = self.calc.ln(value)
        self.current_input = str(result)
        self.result_var.set(self.format_result(result))
        self.new_input = True
    
    def handle_sign_change(self):
        if self.current_input and self.current_input != '0':
            if self.current_input.startswith('-'):
                self.current_input = self.current_input[1:]
            else:
                self.current_input = '-' + self.current_input
            self.result_var.set(self.current_input)
    
    def clear_all(self):
        self.current_input = ""
        self.result_var.set("0")
        self.new_input = True
        self.last_operation = None
        self.operand1 = None
    
    def clear_entry(self):
        self.current_input = ""
        self.result_var.set("0")
    
    def backspace(self):
        if self.current_input and not self.new_input:
            self.current_input = self.current_input[:-1]
            if not self.current_input:
                self.current_input = "0"
            self.result_var.set(self.current_input)
    
    def format_result(self, result):
        if isinstance(result, float):
            if result.is_integer():
                return str(int(result))
            else:
                formatted = f"{result:.10f}".rstrip('0').rstrip('.')
                if len(formatted) > 15:
                    return f"{result:.6e}"
                return formatted
        return str(result)
    
    def show_error(self, message):
        messagebox.showerror("Error", message)
        self.clear_all()


def main():
    root = tk.Tk()
    app = ScientificCalculatorGUI(root)
    root.mainloop()


if __name__ == "__main__":
    main()
