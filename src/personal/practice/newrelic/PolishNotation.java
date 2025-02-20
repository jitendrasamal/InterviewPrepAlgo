package personal.practice.newrelic;


import java.util.Stack;

public class PolishNotation {

    public static void main(String[] args) {
        PolishNotation polishNotation = new PolishNotation();
        String exp = "a+b*(c^d-e)^(f+g*h)-i";
        polishNotation.infixToPostfix(exp);
        polishNotation.infixToPrefix(exp);
    }

    void infixToPrefix(String s) {
        Stack<Character> operators = new Stack<>();
        Stack<String> operands = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the character is an operand, push it to the operands stack
            if (Character.isLetterOrDigit(c)) {
                operands.push(c + "");
            }
            // If the character is '(', push it to the operators stack
            else if (c == '(') {
                operators.push(c);
            }
            // If the character is ')', pop from both stacks and push to the operands stack until '(' is found
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    String op1 = operands.pop();
                    String op2 = operands.pop();
                    char op = operators.pop();
                    String exp = op + op2 + op1;
                    operands.push(exp);
                }
                operators.pop();
            }
            // If an operator is found
            else {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    String op1 = operands.pop();
                    String op2 = operands.pop();
                    char op = operators.pop();
                    String exp = op + op2 + op1;
                    operands.push(exp);
                }
                operators.push(c);
            }
        }

        // Pop all the remaining operators from the stack
        while (!operators.isEmpty()) {
            String op1 = operands.pop();
            String op2 = operands.pop();
            char op = operators.pop();
            String exp = op + op2 + op1;
            operands.push(exp);
        }

        // The final prefix expression is in the operands stack
        System.out.println(operands.pop());
    }

    // Function to perform infix to postfix conversion
    void infixToPostfix(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the scanned character is
            // an operand, add it to the output string.
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
                result.append(c);

                // If the scanned character is
                // an ‘(‘, push it to the stack.
            else if (c == '(')
                st.push('(');

                // If the scanned character is an ‘)’,
                // pop and add to the output string from the stack
                // until an ‘(‘ is encountered.
            else if (c == ')') {
                while (st.peek() != '(') {
                    result.append(st.pop());
                }
                st.pop();
            }

            // If an operator is scanned
            else {
                while (!st.isEmpty() && (precedence(c) < precedence(st.peek()) ||
                        precedence(c) == precedence(st.peek()))) {
                    result.append(st.pop());
                }
                st.push(c);
            }
        }

        // Pop all the remaining elements from the stack
        while (!st.isEmpty()) {
            result.append(st.pop());
        }

        System.out.println(result);
    }

    // Function to return precedence of operators
    int precedence(char c) {
        if (c == '^')
            return 3;
        else if (c == '/' || c == '*')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }
}

