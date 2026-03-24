package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;
import project20280.interfaces.PriorityQueue;

import java.util.*;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for (int j = 0; j < Math.min(keys.length, values.length); j++) {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();;
    }

    // protected utilities
    protected int parent(int j) {return (j-1)/2; }

    protected int left(int j) { return (2*j)+1; }

    protected int right(int j) { return (2*j)+2; }

    protected boolean hasLeft(int j) { return left(j) < heap.size(); }

    protected boolean hasRight(int j) { return right(j) < heap.size(); }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    protected void upheap(int j) {
        while (j > 0){
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) > 0){ break; }
            swap(j, p);
            j = p;
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0){
                    smallChildIndex = rightIndex;
                }
            }

            if (compare(heap.get(smallChildIndex), heap.get(j)) > 0){ break; }

            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        int startIndex = parent(size() - 1);
        for (int j = startIndex; j >= 0; j--) {
            downheap(j);
        }
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newEntry = new PQEntry<>(key, value);
        heap.add(newEntry);
        upheap(heap.size()-1);
        return newEntry;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        if (heap.isEmpty()){ return null; }

        Entry<K, V> root = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove((heap.size() - 1));
        downheap(0);
        return root;
    }

    public static <E> void pqSort(LinkedList<E> S, PriorityQueue<E,?> P){
        int n = S.size();
        for (int j = 0; j < n; j++) {
            E element = S.remove();
            P.insert(element, null);
        }
        for (int j = 0; j < n; j++) {
            E element = P.removeMin().getKey();
            S.addLast(element);
        }
    }

    public String toString() {
        return heap.toString();
    }

    private static <E extends Comparable<E>> void arrayDownHeap(E[] arr, int j, int heapSize) {
        while (true) {
            int left = 2 * j + 1;
            int right = 2 * j + 2;
            int largest = j;

            if (left < heapSize && arr[left].compareTo(arr[largest]) > 0) { largest = left; }

            if (right < heapSize && arr[right].compareTo(arr[largest]) > 0) { largest = right; }

            if (largest == j) { break; }

            arraySwap(arr, j, largest);
            j = largest;
        }
    }
    private static <E> void arraySwap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static <E extends Comparable<E>> void arrayHeapify(E[] arr) {
        int start = (arr.length / 2) - 1;
        for (int j = start; j >= 0; j--) {
            arrayDownHeap(arr, j, arr.length);
        }
    }

    public static <E extends Comparable<E>> void heapSort(E[] arr) {
        arrayHeapify(arr);

        for (int end = arr.length - 1; end > 0; end--) {
            arraySwap(arr, 0, end);
            arrayDownHeap(arr, 0, end);
        }
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        /*
        //original main

        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);
        System.out.println("elements: " + Arrays.toString(rands));
        System.out.println("after adding elements: " + pq);

        System.out.println("min element: " + pq.min());

        pq.removeMin();
        System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]
         */

        //Q5(d)
        /*
        HeapPriorityQueue<Integer, Integer> pqFast = new HeapPriorityQueue<>(rands, rands);
        System.out.println("built with O(n) constructor: " + pqFast);

        HeapPriorityQueue<Integer, Integer> pqInsert = new HeapPriorityQueue<>();
        for (Integer x : rands) {
            pqInsert.insert(x, x);
        }
        System.out.println("built with repeated inserts: " + pqInsert);

        System.out.println("min in fast heap: " + pqFast.min());
        System.out.println("min in insert heap: " + pqInsert.min());

        pqFast.removeMin();
        System.out.println("fast heap after removeMin: " + pqFast);
         */


        //Q6 and Q7
        /*
        int[] sizes = {1000, 5000, 10000, 50000, 100000, 500000, 1000000};
        Random rand = new Random(42);

        System.out.printf("%-12s %-15s %-15s%n", "n", "pqSort (ms)", "heapSort (ms)");

        for (int n : sizes) {
            Integer[] data = new Integer[n];
            for (int i = 0; i < n; i++) {
                data[i] = rand.nextInt();
            }

            LinkedList<Integer> list = new LinkedList<>(Arrays.asList(data));
            Integer[] arr = Arrays.copyOf(data, data.length);

            PriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>();

            long startPQ = System.nanoTime();
            pqSort(list, pq);
            long endPQ = System.nanoTime();

            long startHeap = System.nanoTime();
            heapSort(arr);
            long endHeap = System.nanoTime();

            double pqTimeMs = (endPQ - startPQ) / 1_000_000.0;
            double heapTimeMs = (endHeap - startHeap) / 1_000_000.0;

            System.out.printf("%-12d %-15.3f %-15.3f%n", n, pqTimeMs, heapTimeMs);
        }
         */

    }
}
