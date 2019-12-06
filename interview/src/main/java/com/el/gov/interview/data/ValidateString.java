package com.el.gov.interview.data;

import java.util.Stack;

/**
 * @author Jiangkui
 * @since 2019/12/04 16:35
 */
public class ValidateString {

    public static void main(String[] args) {
        String s1 = "{[()]}";
        String s2 = "{[[)]}";
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        if (s.equals("")) {
            return true;
        } else {
            char[] arr = s.toCharArray();
            for (char c : arr) {
                if (stack.size() == 0) {
                    stack.push(c);
                } else if (isQ(stack.peek(), c)) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

        }
        return stack.size() == 0;
    }


    private boolean isQ(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }

}
