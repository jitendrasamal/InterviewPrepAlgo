package personal.practice.educativeio.customdatastructures;

import java.util.Stack;

//https://leetcode.com/problems/min-stack/description/
//https://www.educative.io/courses/grokking-coding-interview/min-stack

public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> sortedStack;
    public MinStack() {
        stack = new Stack<>();
        sortedStack = new Stack<>();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }

    public void push(int val) {
        stack.push(val);
        if (sortedStack.isEmpty() || sortedStack.peek() >= val) {
            sortedStack.push(val);
        }
    }

    public int getMin() {
        return sortedStack.peek();
    }

    public void pop() {
        if (stack.isEmpty()) return;
        int top = stack.pop();
        if (sortedStack.peek() == top) sortedStack.pop();
    }

    public int top() {
        return stack.peek();
    }
}
