package project20280.stacksqueues;

import java.util.Scanner;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public boolean check() {
        LinkedStack<Character> stack = new LinkedStack<>();



        for (int i = 0; i < input.length(); i++) {
            char chara = input.charAt(i);
            //System.out.println(chara);
            if (chara == '{' || chara == '[' || chara == '(') {
                //System.out.println("Character pushed");
                stack.push(chara);
            }

            if (chara == '}' || chara == ']' || chara == ')') {
                if (!stack.isEmpty())
                {
                    char verify = stack.pop();
                    //System.out.println("Character popped");
                    if (chara == ')' && verify != '(') {
                        //System.out.println(" ')' found, but no '('");
                        break;
                    }
                    if (chara == '}' && verify != '{') {
                        //System.out.println(" '}' found, but no '{'");
                        break;
                    }
                    if (chara == ']' && verify != '[') {
                        //System.out.println(" ']' found, but no '['");
                        break;
                    }
                }
                else {
                    //System.out.println("Error, Stack is Empty");
                    break;
                }

            }
        }
        if (stack.isEmpty()){
            System.out.println("No Issues");
        } else {
            System.out.println("Remaining Parentheses leftover");
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}