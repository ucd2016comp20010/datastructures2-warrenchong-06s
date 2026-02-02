package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int index) {
        //Validation check
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Position entered is out of range of the linked list");
        }

        //Set the next node after the tail (or the "head") as the current node of the linked list
        Node<E> cur = tail.next;

        //Loop for n amount of times to reach the desired index
        for (int i = 0; i < index; i++) {
            //System.out.println("Current Element: " + cur.getData());
            cur = cur.next;
        }

        //Return the data of the node of the desired index
        return cur.data;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int index, E e) {
        //Validation check
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Position entered is out of range of the linked list");
        }

        if (size == 0){
            addFirst(e);
        }

        else if (index == size - 1) {
            addLast(e);
        }

        //Set the next node after the tail (or the "head") as the current node of the linked list
        Node<E> cur = tail.next;

        //Loop for n amount of times to reach the desired index, subtracted by one to account for starting at
        //the head (tail.next) instead of the tail
        for (int i = 0; i < index - 1; i++) {
            //System.out.println("Current Element: " + cur.getData());
            cur = cur.next;
        }

        //Insert a node with the element at the current position
        Node<E> newNode = new Node<>(e, cur.next);
        cur.next = newNode; //Update the current's destination with the new node
        size++; // Increase the size of the list
    }

    @Override
    public E remove(int index) {
        //Check if the linked list is empty
        if (tail == null) {
            System.out.println("Circular Linked List is empty");
            return null;
        }

        if (index == 0) {
            return removeFirst();
        }
        else if (index == size - 1) {
            return removeLast();
        }
        else{
            //Find the head node
            Node <E> cur = tail.next;

            //Loop throught list to the targeted index
            for (int i = 0; i < index; i++){
                cur = cur.next;
            }

            // Set the tail as the index's predecessor
            tail.next = cur;

            size--; //reduce the size
            return cur.data; //return the data of the deleted node
        }
    }

    public void rotate() {
        // TODO
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        //Check if the linked list is empty
        if (tail == null) {
           System.out.println("Circular Linked List is empty");
           return null;
        }

        //Find the head node
        Node <E> cur = tail.next;

        //Set the new head node as the head's destination
        tail.next = cur.next;

        size--; //Reduce size
        return cur.data; //Return data of removed node
    }

    @Override
    public E removeLast() {
        //Check if the linked list is empty
        if (tail == null) {
            System.out.println("Circular Linked List is empty");
            return null;
        }

        //Find the head node
        Node <E> cur = tail.next;

        //Loop to the second last value in the list
        for (int i = 0; i < size - 1; i++){
            cur = cur.next;
        }

        // Set the tail as the second last value
        tail.next = cur;

        size--; //reduce the size
        return cur.data; //return the data of the deleted node
    }

    @Override
    public void addFirst(E e) {
        //If the list is empty
        if (tail == null){

            //Create the first node and set it as the end.
            tail = new Node<>(e, null);
            tail.next = tail;
        } else {
            //Else, Create the new node, but DON'T set it as the end
            Node<E> head = tail.next;
            Node<E> newNode = new Node<>(e, head);
            tail.next = newNode;
        }

        size++;
    }

    @Override
    public void addLast(E e) {
        //insert a new node, and then set it as the new tail
        addFirst(e);
        tail = tail.next;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        /*
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);


        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
        */


        List<Integer> lli = new CircularlyLinkedList<Integer>();
        lli.addLast(1);
        lli.addLast(2);
        lli.addLast(3);
        assertEquals("[1, 2, 3]", lli.toString());
        lli.get(1);
        //assertEquals(2, lli.get(1), "did not get right element(1)");
        //assertEquals(3, lli.get(2), "did not get right element(2)");

    }
}
