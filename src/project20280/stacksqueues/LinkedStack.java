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
}
