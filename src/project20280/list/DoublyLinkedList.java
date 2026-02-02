package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public DoublyLinkedList() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.next = trailer;
    }

    public int getSize(){
        return size;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newNode = new Node<E>(e, pred, succ);
        pred.next = newNode;
        succ.prev = newNode;
    }

    @Override
    public int size() {
        //Start the loop at the head, instead of the header
        Node<E> cur = header.next;
        int count = 0;

        if (cur.data == null){
            return count;
        }

        while (cur != null) {
            //Skip Trailer in Count
            if (cur.next != trailer){
                count++;
            }
            cur = cur.next;
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        if (header.next.data == null) {
            return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Position entered is out of range of the linked list");
        }

        //Set current node as the head of the linked list
        Node<E> cur = header.next;

        //Loop for n amount of times to reach the desired index
        for (int i = 0; i < index; i++) {
            cur = cur.next;

        }

        //Return the data of the node of the desired index
        return cur.data;
    }

    @Override
    public void add(int index, E e) {
        //Argument Validation Check
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Position entered is out of range of the linked list");
        }

        //Check if Index is 0, if so, use the addFirst function
        if (index == 0){
            addFirst(e);
        }

        //Check if Index is the last element in the list, if so, use addLast
        else if (index == size - 1){
            addLast(e);

        }

         else {
            //Set current node as the head of the linked list
            Node<E> cur = header.next;

            //Loop for n amount of times to reach the desired index
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }

            addBetween(e, cur.prev, cur);
        }

    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Position entered is out of range of the linked list");
        }

        //Set current node as the head of the linked list
        Node<E> cur = header.next;

        //Loop for n amount of times to reach the desired index
        for (int i = 0; i < index; i++) {
            cur = cur.next;

        }

        //Run the privater remove function on the current node, and return the data from the removed node
        return remove(cur);
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) header.next;

        @Override
        public boolean hasNext() {
            return curr != trailer;
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
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        //Set current as the head of the Linked List
        Node <E> cur = header;
        Node <E> targetNode = null;

        //System.out.println("Testing private remove function.");

        //Loop to the end of the list
        while (cur.next != null){
            //System.out.println("Current element: " + cur.data);
            //If current's destination is the target node
            if (cur.next == n){
                //Set the target node
                targetNode = cur.next;

                //Link the current's new destination as the target node's destination
                cur.next = targetNode.next;

                //Link the target node's predecessor as the current node
                targetNode.next.prev = cur;
            }

            //Continue looping through the list
            cur = cur.next;
        }

        size--; // Reduce the size
        //Return the data from the removed target node
        return targetNode.getData();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return header.next.getData();
    }

    public E last() {
        if (isEmpty()) {return null;}
        return get(size - 1);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        return remove(header.next);

    }

    @Override
    public E removeLast() {
        if (isEmpty()){
            return null;
        }
        return remove(trailer.prev);
    }

    @Override
    public void addLast(E e) {
        addBetween(e, trailer.prev, trailer);
        size++;
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, header, header.next);
        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = header.next;
        while (curr != trailer) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != trailer) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);

            System.out.print("Check count: " + ll.size());
        }
    }
}