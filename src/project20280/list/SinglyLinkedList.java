package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;
import java.util.LinkedList;

public class SinglyLinkedList<E extends Comparable<E>> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public E get(int position) {

            if (position < 0 || position > size) {
                throw new IllegalArgumentException("Position entered is out of range of the linked list");
            }

            Node<E> cur = (Node<E>) head;
            E target = head.getElement();
            for (int i = 0; i < position; i++) {
                cur = cur.next;
                target = cur.getElement();
            }
            return target;
    }

    @Override
    public void add(int position, E e) {
        //Check if user wants to add at the start of the linked list

        if  (position < 0 || position > size) {
            throw new IllegalArgumentException("Position entered is out of range of the linked list");
        }

        if (position == 0){
            //if so, use the addFirst Function instead
            addFirst(e);
        }
        else {
            Node<E> cur = (Node<E>) head;
            //Count is 1, because if you wanted to add an element to the start, use addFirst() instead
            int count = 1;

            //Loop to check end of Linked List
            while (cur.getNext() != null) {

                //Check if this position is the desired index
                if (count == position) {

                    // if so, create a new node with the given element and the current position's destination as the next node
                    Node<E> newNode = new Node<>(e, cur.next);

                    //Set the current position's destination as the new node
                    cur.next = newNode;
                }

                //Add to tracker if not the position
                count++;

                //Keep moving through the Linked List
                cur = cur.next;
            }
            size++;

        }

    }


    @Override
    public void addFirst(E e) {
        head = new Node<>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        Node<E> last = head;
        if (last == null) {
            head = newest;
        }
        else {
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newest);
        }
        size++;
    }

    @Override
    public E remove(int position) {

        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Position entered is out of range of the linked list");
        }

        Node<E> cur = (Node<E>) head;
        //Count is 1, because if you wanted to remove an element to the start, use removeFirst() instead
        int count = 1;
        Node<E> targetNode = null; //Node to be removed

        //Loop to check end of Linked List,
        while (cur != null) {

            //Check if this position is the desired index
            if (count == position) {

                targetNode = cur.next; // if so, save the target node of the current node

                size--; //Reduce the size of the list

                //Set the current position's destination as that target node's destination node
                cur.next = targetNode.next;

            }

            count++; //Add to tracker if not the position

            cur = cur.next; //Keep moving through the Linked List

        }

        if (targetNode != null) {
            return targetNode.getElement(); //Return the target node's element
        } else  {  //This case shouldn't occur as targetNode should be filled at some point, but in case it doesn't...
            System.out.println("Target Node is null for some reason...");
            return null;
        }

    }

    @Override
    public E removeFirst() {
        if (head == null) {
            System.out.println("Head is null, Your Linked is Empty");
            return null;
        }

        //Get the first element from the head
        E firstElement = head.getElement();

        //Set the new head of the Linked List as the next element of the inital head
        head = head.getNext();

        //Reduce the size of the Linked List
        size--;
        return firstElement;
    }

    @Override
    public E removeLast() {
        if (head == null) {
            System.out.println("Head is null, Your Linked is Empty");
            return null;
        }

        Node<E> cur = (Node<E>) head;
        Node<E> targetNode; //Node to be removed

        //System.out.println("Current Size: " + size);

        //If there's only one element in the Linked list, use the remove first function instead.
        if (size == 1){
            return removeFirst();
        }

        //Loop to the second last index of the list
        while  (cur.next.next != null) {
            //System.out.println("Current Element: " + cur.getElement());
            cur = cur.next; //Keep moving through the Linked List to the end

        }

        targetNode = cur.next; // Save the target node of the current node, as the one to be eliminated
        System.out.println("Target Node: " + targetNode.getElement());
        cur.next = null; //Set the destination node's next point as null.
        size--; //Reduce the size of the list
        return targetNode.getElement(); //Return the target node's element

    }

    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> targetList) {
        //Merged Linked List
        SinglyLinkedList<E> mergedList = new SinglyLinkedList<>();

        //Set pointers for both linked lists
        Node<E> ll1cur = head;
        Node<E> ll2cur = targetList.head;

        //Loop through both linked lists until an endpoint is reached
        while (ll1cur != null && ll2cur != null) {
            //System.out.println("Current Node for LL1: " + ll1cur.getElement());
            //System.out.println("Current Node for LL2: " + ll2cur.getElement());

            //Compare the current nodes, if the node of LL1 is less than LL2
            if (ll1cur.getElement().compareTo(ll2cur.getElement()) < 0) {
                //Add that element to the merge and move the pointer to the next node
                mergedList.addLast(ll1cur.getElement());
                ll1cur = ll1cur.next;
            } else {
                //Else, add the element of the node of LL2 to the merge, and move its pointer instead
                mergedList.addLast(ll2cur.getElement());
                ll2cur = ll2cur.next;
            }
        }

        //If the end of the first linked list is reached
        if (ll1cur == null) {
            //continue adding the elements of the second linked list to the merge
            while (ll2cur != null) {
                //System.out.println("Current Node for LL2: " + ll2cur.getElement());
                mergedList.addLast(ll2cur.getElement());
                ll2cur = ll2cur.next;
            }
        }

        //If the end of the second linked list is reached
        if (ll2cur == null) {
            //continue adding the elements of the first linked list to the merge
            while (ll1cur != null) {
                //System.out.println("Current Node for LL1: " + ll1cur.getElement());
                mergedList.addLast(ll1cur.getElement());
                ll1cur = ll1cur.next;
            }
        }

        return mergedList;
    }

    public void reverse(){


        Node<E> prev = null; //Used to build the reverse linked list, building backwards
        Node<E> cur = head;  // Current node to reverse
        Node<E> next;        // Use to store the next node after the current.

        //Loop to the end of the list
        while (cur != null){
            //next moves to the next unvisited node in the list
            next = cur.next;

            //this unvisited node is then built onto the reversed list
            cur.next = prev;

            //prev moves to the current node in the list
            prev = cur;

            //Cur moves to the next node on the list
            cur = next;
        }
        head = prev;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        //System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        /*
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        System.out.println(ll);
        //ll.removeLast();
        ll.removeFirst();
        System.out.println("I accept your apology");
        ll.add(1, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

        System.out.println("Element at position 2: " + ll.get(2));

        //Check functionality of add()
        //ll.add(2, 100);
        //System.out.println(ll);

        //Check functionality of remove()
        ll.remove(2);
        System.out.println("");


        System.out.println("Check functionality of removeFirst()");
        ll.removeFirst();
        System.out.println(ll);
        System.out.println("");

        System.out.println("Check functionality of removeLast()");
        ll.removeLast();
        System.out.println(ll);
        */

        /*
        // Test sortedMerge()
        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<Integer>();
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<Integer>();
        l1.addFirst(24);
        l1.addFirst(20);
        l1.addFirst(6);
        l1.addFirst(2);

        l2.addFirst(25);
        l2.addFirst(19);
        l2.addFirst(12);
        l2.addFirst(8);
        l2.addFirst(5);
        l2.addFirst(3);
        l2.addFirst(1);


        //The resulting list is:
        SinglyLinkedList<Integer> result = l1.sortedMerge(l2);
        System.out.println(result);
        // result = {1, 2, 3, 5, 6, 8, 12, 19, 20, 24, 25};
        */

        //Test Reverse
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<Integer>();
        l2.addLast(1);
        l2.addLast(2);
        l2.addLast(3);
        l2.addLast(4);
        System.out.println("\nTest 4 - [1,2,3,4]: " + l2.toString());
        l2.reverse();
        System.out.println("After reverse: " + l2.toString());
    }
}
