package project20280.stacksqueues;

import project20280.interfaces.Stack;
import project20280.list.DoublyLinkedList;

public class LinkedStack<E> implements Stack<E> {

    DoublyLinkedList<E> dll = new DoublyLinkedList<>();

    public static void main(String[] args) {
    }

    public LinkedStack() {

    }

    @Override
    public int size() {
        return dll.size();
    }

    @Override
    public boolean isEmpty() {
        return dll.isEmpty();
    }

    @Override
    public void push(E e) {
        dll.addFirst(e);
    }

    @Override
    public E top() {
        return dll.first();
    }

    @Override
    public E pop() {
        return dll.removeFirst();
    }

    public String toString() {
        return dll.toString();
    }

    static String convertToBinary(long dec_num) {
        LinkedStack<String> stringStack = new LinkedStack<>();
        String returnStr = "";
        long remainder = dec_num;

        //While there is still a remainder
        while (remainder != 0) {
            long modulo = remainder % 2; //Compute Binary Value
            stringStack.push(String.valueOf(modulo)); // Push the Binary onto the stack
            remainder = remainder / 2; // Divide the remainder

        }

        //While the stack is not empty
        while (!stringStack.isEmpty()) {
            //Pop the stack and add it onto the string.
            String string = stringStack.pop();
            returnStr += string;
        }

        return returnStr;




    }

}




  