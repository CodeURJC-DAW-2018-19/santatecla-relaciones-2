package urjcdaw12.relman.trees;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedTree<E> implements NAryTree<E> {

    /**
     * Inner class which represents a node of the tree
     *
     * @param <T> the type of the elements stored in a node
     */
    private class TreeNode<T> implements Position<T> {

        private T element; // The element stored in the position
        private TreeNode<T> parent; // The parent of the node
        private List<TreeNode<T>> children; // The children of the node
        private LinkedTree<T> myTree; // A reference to the tree where the node belongs

        /**
         * Constructor of the class
         * 
         * @param t the tree where the node is stored
         * @param e the element to store in the node
         * @param p the parent of the node
         * @param c the list of children of the node
         */
        public TreeNode(LinkedTree<T> t, T e, TreeNode<T> p, List<TreeNode<T>> c) {
            this.element = e;
            this.parent = p;
            this.children = c;
            this.myTree = t;
        }

        @Override
        public T getElement() {
            return element;
        }

        /**
         * Sets the element stored at this position
         * 
         * @param o the element to store in the node
         */
        public final void setElement(T o) {
            element = o;
        }

        /**
         * Accesses to the list of children of this node
         * 
         * @return the list of children
         */
        public List<TreeNode<T>> getChildren() {
            return children;
        }

        /**
         * Sets the children of this node
         * 
         * @param c the list of nodes to be used as children of this position
         */
        public final void setChildren(List<TreeNode<T>> c) {
            children = c;
        }

        /**
         * Accesses to the parent of this node
         * 
         * @return the parent of this node
         */
        public TreeNode<T> getParent() {
            return parent;
        }

        /**
         * Sets the parent of this node
         * 
         * @param v the node to be used as parent
         */
        public final void setParent(TreeNode<T> v) {
            parent = v;
        }

        /**
         * Consults the tree in which this node is stored
         * 
         * @return a reference to the tree where the node belongs
         */
        public LinkedTree<T> getMyTree() {
            return myTree;
        }

        /**
         * Sets the tree where this node belongs
         * 
         * @param myTree the tree where this node belongs
         */
        public void setMyTree(LinkedTree<T> myTree) {
            this.myTree = myTree;
        }
    }

    private TreeNode<E> root; // The root of the tree
    private int size; // The number of nodes in the tree

    /**
     * Creates an empty tree.
     */
    public LinkedTree() {
        root = null;
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !isLeaf(v);
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        return (node.getChildren() == null) || (node.getChildren().isEmpty());
    }

    @Override
    public boolean isRoot(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        return (node == this.root());
    }

    @Override
    public Position<E> root() throws RuntimeException {
        if (root == null) {
            throw new RuntimeException("The tree is empty");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws RuntimeException {
        TreeNode<E> node = checkPosition(p);
        Position<E> parentPos = node.getParent();
        if (parentPos == null) {
            throw new RuntimeException("The node has not parent");
        }
        return parentPos;
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        return node.getChildren();
    }


    public E replace(Position<E> p, E e) {
        TreeNode<E> node = checkPosition(p);
        E temp = p.getElement();
        node.setElement(e);
        return temp;
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree already has a root");
        }
        size = 1;
        root = new TreeNode<E>(this, e, null, new ArrayList<>());
        return root;
    }

    public void swapElements(Position<E> p1, Position<E> p2) {
        TreeNode<E> node1 = checkPosition(p1);
        TreeNode<E> node2 = checkPosition(p2);
        E temp = p2.getElement();
        node2.setElement(p1.getElement());
        node1.setElement(temp);
    }

    /**
     * Validates the given position, casting it to TreeNode if valid
     * @param p the position to be converted
     * @return the position casted to TreeNode
     * @throws IllegalStateException if the position is not valid
     */
    private TreeNode<E> checkPosition(Position<E> p)
            throws IllegalStateException {
        if (p == null || !(p instanceof TreeNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        TreeNode<E> aux = (TreeNode<E>) p;

        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }

    public Position<E> add(E element, Position<E> p) {
        TreeNode<E> parent = checkPosition(p);
        TreeNode<E> newNode = new TreeNode<E>(this, element, parent, new ArrayList<>());
        List<TreeNode<E>> l = parent.getChildren();
        l.add(newNode);
        size++;
        return newNode;
    }

    public void remove(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        if (node.getParent() != null) {
            Iterator<Position<E>> it = new BFSIterator<>(this, p);
            int cont = 0;
            while (it.hasNext()) {
                TreeNode<E> next = checkPosition(it.next());
                next.setMyTree(null);
                cont++;
            }
            size = size - cont;
            TreeNode<E> parent = node.getParent();
            parent.getChildren().remove(node);
        } else {
            this.root = null;
            this.size = 0;
        }
        node.setMyTree(null);
    }
    
    @Override
    public Iterator<Position<E>> iterator() {
        return new BFSIterator<>(this);
    }

    @Override
    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {
        TreeNode<E> origen =checkPosition(pOrig);
        TreeNode<E> destino= checkPosition(pDest);
        BFSIterator<E> iterator= new BFSIterator<>(this,pOrig);
        boolean encontrado=false;
        while (iterator.hasNext() && !encontrado){
            if (iterator.next()==pDest){
                encontrado=true;
            }
        }
        if (!encontrado){
            destino.children.add(origen);
            origen.getParent().getChildren().remove(origen);
            origen.setParent(destino);

        }else{
            throw new RuntimeException("The destination node cannot be a subtree of the origin node");
        }
    }
}

