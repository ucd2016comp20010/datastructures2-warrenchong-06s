package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.DoublyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

    private DoublyLinkedList<E> dll = new DoublyLinkedList<>();

    public static void main(String[] args) {
    }

    public LinkedQueue() {
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
    public void enqueue(E e) {
        dll.addLast(e);
    }

    @Override
    public E first() {
        return dll.first();
    }

    @Override
    public E dequeue() {
        return dll.removeFirst();
    }

    public String toString() {
        return dll.toString();
    }
}
