package urjcdaw11.relman.trees;

public interface Tree<E> extends Iterable<Position<E>> {

	/**
	 * Checks the number of nodes in the tree.
	 *
	 * @return the number of nodes in the tree
	 */
	public int size();

	/**
	 * Checks if the tree is empty.
	 * 
	 * @return TRUE if the tree is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Checks the root of the tree.
	 * 
	 * @return the root of the tree
	 * @throws RuntimeException if the tree is empty
	 */
	public Position<E> root() throws RuntimeException;

	/**
	 * Consults the parent of a given node.
	 * 
	 * @param v the position of the node whose parent is being consulted
	 * @return the position of the parent of v
	 * @throws RuntimeException if the node does not have a parent (i.e., it is the
	 *                          root)
	 */
	public Position<E> parent(Position<E> v) throws RuntimeException;

	/**
	 * Creates an iterable collection of the children of a given node.
	 * 
	 * @param v the position of the node whose children are checked
	 * @return an iterable collection with the children of the consulted node
	 */
	public Iterable<? extends Position<E>> children(Position<E> v);

	/**
	 * Checks if a given node is internal.
	 * 
	 * @param v the position of the node to check
	 * @return TRUE is the node is internal, FALSE otherwise
	 */
	public boolean isInternal(Position<E> v);

	/**
	 * Checks if a given node is external.
	 * 
	 * @param v the position of the node to check
	 * @return TRUE if the node is a leaf, FALSE otherwise
	 * @throws RuntimeException if the position is not valid
	 */
	public boolean isLeaf(Position<E> v) throws RuntimeException;

	/**
	 * Checks if a given node is the root of the tree.
	 * 
	 * @param v the position of the node to check
	 * @return TRUE if the node is the root, FALSE otherwise
	 */
	public boolean isRoot(Position<E> v);

	/**
	 * Sets the root of the tree
	 * 
	 * @param e the element to be used as a root
	 * @return the position of the root added
	 * @throws RuntimeException if the tree already has a root
	 */
	public Position<E> addRoot(E e) throws RuntimeException;
}
