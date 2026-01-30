package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

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
            // TODO
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
        Node<E> cur = (Node<E>) head;
        //Count is 1, because if you wanted to add an element to the start, use addFirst() instead
        int count = 1;

        //Loop to check end of Linked List
        while (cur.next != null) {

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
        return null;
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
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        System.out.println(ll);
        ll.removeLast();
        ll.removeFirst();
        System.out.println("I accept your apology");
        ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

        System.out.println("Element at position 2: " + ll.get(2));

        //Check functionality of add()
        ll.add(2, 100);
        System.out.println(ll);

        //Check functionality of remove()
        ll.remove(2);
        System.out.println(ll);

    }
}
