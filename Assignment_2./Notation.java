import java.lang.Object;
import java.util.Stack;

public class Notation extends Object {

	public Notation() {

	}

	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		Stack<Double> stack = new Stack<>();

		// Traverse through each character of the postfix expression
		for (int i = 0; i < postfixExpr.length(); i++) {
			char ch = postfixExpr.charAt(i);

			// If it's a space, ignore it
			if (Character.isWhitespace(ch)) {
				continue;
			}

			// If it's a digit, push it onto the stack as a double
			if (Character.isDigit(ch)) {
				stack.push((double) (ch - '0'));
			}
			// If it's an operator, pop two operands and apply the operator
			else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				if (stack.size() < 2) {
					throw new InvalidNotationFormatException();
				}

				// Pop the top two values from the stack
				double operand2 = stack.pop(); // This is the right operand
				double operand1 = stack.pop(); // This is the left operand
				double result;

				// Perform the operation based on the operator
				switch (ch) {
				case '+':
					result = operand1 + operand2;
					break;
				case '-':
					result = operand1 - operand2;
					break;
				case '*':
					result = operand1 * operand2;
					break;
				case '/':
					if (operand2 == 0) {
						throw new InvalidNotationFormatException();
					}
					result = operand1 / operand2;
					break;
				default:
					throw new InvalidNotationFormatException();
				}

				// Push the result back onto the stack
				stack.push(result);
			}
			// If an invalid character is encountered, throw an exception
			else {
				throw new InvalidNotationFormatException();
			}
		}

		// At the end, there should be exactly one element in the stack
		if (stack.size() != 1) {
			throw new InvalidNotationFormatException();
		}

		// Return the final result
		return stack.pop();
	}

	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		Stack<String> stack = new Stack<>();

		
		for (int i = 0; i < postfix.length(); i++) {
			char ch = postfix.charAt(i);

			if (ch == ' ') {
				continue;
			}

			
			if (Character.isDigit(ch)) {
				stack.push(String.valueOf(ch));
			}
			
			else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				if (stack.size() < 2) {
					throw new InvalidNotationFormatException();
				}

				String operand2 = stack.pop();
				String operand1 = stack.pop();

				
				String infix = "(" + operand1 + ch + operand2 + ")";
				stack.push(infix);
			}
			
			else {
				throw new InvalidNotationFormatException();
			}
		}

		
		if (stack.size() != 1) {
			throw new InvalidNotationFormatException();
		}

		
		return stack.pop();
	}

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		Stack<Character> stack = new Stack<>();
		StringBuilder postfix = new StringBuilder();

		
		for (int i = 0; i < infix.length(); i++) {
			char ch = infix.charAt(i);

			
			if (ch == ' ') {
				continue;
			}

			
			if (Character.isDigit(ch)) {
				postfix.append(ch);
			}
			
			else if (ch == '(') {
				stack.push(ch);
			}
			
			else if (ch == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					postfix.append(stack.pop());
				}
				if (stack.isEmpty()) {
					throw new InvalidNotationFormatException();
				}
				stack.pop(); 
			}

			else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
					postfix.append(stack.pop());
				}
				stack.push(ch);
			}
			
			else {
				throw new InvalidNotationFormatException();
			}
		}

		
		while (!stack.isEmpty()) {
			if (stack.peek() == '(' || stack.peek() == ')') {
				throw new InvalidNotationFormatException();
			}
			postfix.append(stack.pop());
		}

		return postfix.toString();
	}
	
	
	private static int precedence(char operator) {
	    switch (operator) {
	        case '+':
	        case '-':
	            return 1;
	        case '*':
	        case '/':
	            return 2;
	        default:
	            return -1; 
	    }
	}


}
