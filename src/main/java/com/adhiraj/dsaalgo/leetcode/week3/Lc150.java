package com.adhiraj.dsaalgo.leetcode.week3;

import java.util.Stack;

// Use stack. Iterate from start. When operator comes pop two operands -> calc -> add back to stack.
// Similar for prefix. Iterate from end.
// Check how to convert pre/post-fix notation, given infix.
public class Lc150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> operands = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String curr = tokens[i];
            if (!curr.equals("+") && !curr.equals("-") && !curr.equals("*") && !curr.equals("/") ) {
                operands.push(Integer.parseInt(curr));
                continue;
            }

            int second = operands.pop(), first = operands.pop(), eval;
            switch(curr){
                case "+":
                    eval = first + second;
                    break;
                case "-":
                    eval = first - second;
                    break;
                case "*":
                    eval = first * second;
                    break;
                default:
                    // case "/":
                    eval = first / second;
            }
            operands.push(eval);
        }

        return operands.pop();
    }
}
