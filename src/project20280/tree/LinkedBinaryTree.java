package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.Random;
//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static Random rnd = new Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {

        /*
        //Q1 - Week 4

        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
        */

        //h(i) - Week 3
        /*
        Integer [] arr2 = new Integer [] {1,
                2,3,
                4,5,6,7,
                8,9,10,11,12,13,14,15,
                16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                null ,null ,null ,35};

        LinkedBinaryTree<Integer> bt2 = new LinkedBinaryTree<Integer>();
        bt2.createLevelOrder(arr2);
        System.out.println("New tree: " + bt2.toBinaryTreeString());
        System.out.println("The height of the tree is " + bt2.height_recursive(bt2.root()));
        System.out.println("The amount of calls taken by the function is " + callCount);
        System.out.println("The diameter of the tree is " + bt2.getDiameter());
        */
        Integer []inorder = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        Integer []preorder = {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16,17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};

        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.construct(inorder, preorder);
        System.out.println(bt.toBinaryTreeString());
        //System.out.println(bt.rootToLeafPaths());
        bt.printLeafNodes();




    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public Node<E> construct(E[] inorder, E[] preorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length) {
            System.out.println("Either one of the arrays (or both) is empty, or they aren't of the same length");
            return null;
        }
        int [] preOrderIndex = {0};
        this.root = constructRecursive(inorder, preorder, 0, inorder.length - 1, preOrderIndex, root);
        return this.root;
    }

    private Node<E> constructRecursive(E[] inorder, E[] preorder, int inorderStart, int inorderEnd, int[] preOrderIndex, Node<E> parent) {
        //Base Case: Empty Subtree
        if (inorderStart > inorderEnd) {
            return null;
        }

        //Root is the next element in the preorder
        E rootValue = preorder[preOrderIndex[0]++];
        Node<E> root = new Node<>(rootValue, null, null, null);

        int inOrderIndex = findInorderIndex(inorder, inorderStart, inorderEnd, rootValue);
        root.left = constructRecursive(inorder, preorder, inorderStart, inOrderIndex - 1, preOrderIndex, root);
        root.right = constructRecursive(inorder, preorder, inOrderIndex + 1, inorderEnd, preOrderIndex, root);

        return root;

    }

    private int findInorderIndex(E[] inorder, int inorderStart, int inorderEnd, E e) {
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) {
            System.out.println("Tree is not Empty");
            return null;
        }
        root = createNode(e, null,  null, null);
        size++;
        return root;
    }

    public void insert(E e) {
        if (isEmpty()){
            addRoot(e);
        } else {
            root = addRecursive(root, e);
        }
    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {

        if (p == null) {
            size++;
            return createNode(e, p,  null, null);
        }
        Comparable<? super E> key = (Comparable<? super E>) e;
        if (key.compareTo(p.getElement()) < 0) {
            Node<E> left = addRecursive(p.getLeft(), e);
            p.setLeft(left);
            left.setParent(p);
        } else {
            Node<E> right = addRecursive(p.getRight(), e);
            p.setRight(right);
            right.setParent(p);
        }

        return p;
    }


    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null){
            System.out.println("This already has a left Child node");
            return null;
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null){
            System.out.println("This already has a right Child node");
            return null;
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> targetNode = validate(p);
        E oldData = targetNode.getElement();
        targetNode.setElement(e);
        return oldData;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> targetNode = validate(p);

        if (targetNode != null){
            throw new IllegalArgumentException("This node is not a leaf, it contains a child node");
        }

        size += t1.size + t2.size;

        if (!t1.isEmpty()){
            t1.root.setParent(targetNode);
            targetNode.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }

        if (!t2.isEmpty()){
            t2.root.setParent(targetNode);
            targetNode.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }



    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        if (numChildren(p) == 2) {
            System.out.println("Cannot remove node as it is a proper node with 2 children");
            return null;
        }
        Node<E> parent = validate(p);
        Node<E> child = null;

        //Determine which node is the child node containing data
        if (parent.getLeft() != null) {
            child = parent.getLeft();
        }
        if (parent.getRight() != null) {
            child = parent.getRight();
        }

        //Set new links for the Child node if there was a Grandparent
        //Grandparent -> Parent -> Child
        //Grandparent -> Child
        if (child != null){
            child.setParent(parent.getParent());
        }

        // if the parent was the root node, set the child as the new root
        if (parent == root) {
            root = child;
        }
        //Else, find out the location of Parent node as a child to the grandparent
        else {
            Node<E> grandparent = parent.getParent();

            //Set the new child node of the grandparent to be the child node of the parent
            if (parent == grandparent.getLeft()) {
                grandparent.setLeft(child);
            }
            else {
                grandparent.setRight(child);
            }
        }
        size--; //Reduce Size
        E oldData = parent.getElement(); //Retrieve old Data

        //Remove connections
        parent.setElement(null);
        parent.setLeft(null);
        parent.setRight(null);
        parent.setParent(parent);

        return oldData;
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        root = createLevelOrderHelper(l, root, 0);
    }

    private Node<E> createLevelOrderHelper(ArrayList<E> l, Node<E> p, int i) {
        if (l.isEmpty()) {
            System.out.print("The List is empty");
            return null;
        }
        if (i < l.size()){
            Node<E> n = createNode(l.get(i), p, null, null);
            n.left = createLevelOrderHelper(l, n.left, (2*i)+1);
            n.right = createLevelOrderHelper(l, n.right, (2*i)+2);
            ++size;
            return n;
        }
        return p;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        if (arr.length <= 0){
            System.out.print("The Array is empty");
            return null;
        }

        if (i < arr.length){
            Node<E> n = createNode(arr[i], p, null, null);
            n.left = createLevelOrderHelper(arr, n.left, (2*i)+1);
            n.right = createLevelOrderHelper(arr, n.right, (2*i)+2);
            ++size;
            return n;
        }
        return p;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    public ArrayList<ArrayList<E>> rootToLeafPaths() {
        ArrayList<ArrayList<E>> paths = new ArrayList<>();

        if (!isEmpty()) {
            ArrayList<E> currentPath = new ArrayList<>();
            collectPaths(root(), currentPath, paths);
        }

        return paths;
    }

    private void collectPaths(Position<E> p, ArrayList<E> currentPath, ArrayList<ArrayList<E>> paths) {
        if (p == null) {
            return;
        }

        currentPath.add(p.getElement());

        if (isExternal(p)) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            Position<E> leftChild = left(p);
            Position<E> rightChild = right(p);

            if (leftChild != null) {
                collectPaths(leftChild, currentPath, paths);
            }
            if (rightChild != null) {
                collectPaths(rightChild, currentPath, paths);
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public void printLeafNodes() {
        printLeafNodesHelper(root());
        System.out.println();
    }

    private void printLeafNodesHelper(Position<E> p) {
        if (p == null) {
            return;
        }

        if (isExternal(p)) {
            System.out.print(p.getElement() + " ");
            return;
        }

        printLeafNodesHelper(left(p));
        printLeafNodesHelper(right(p));
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }

    private static int diameter = 0;
    public int diameter_recursive(Position<E> p) {
        Position<E> leftChild = left(p);    // null if no left
        Position<E> rightChild = right(p);  // null if no right

        int left = 0;
        int right = 0;

        if (leftChild != null) {
            left = diameter_recursive(leftChild);
        }

        if (rightChild != null) {
            right = diameter_recursive(rightChild);
        }

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }

    public int getDiameter() {
        diameter = 0;
        if (!isEmpty()) {
            diameter_recursive(root());
        }
        return diameter;  // 10 for your array!
    }


}


