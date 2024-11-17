import java.util.ArrayList;

/**
 * Generic interface for a linked converter tree.
 *
 * @param <T> The type of data stored in the tree.
 */
public interface LinkedConverterTreeInterface<T> {
    
    /**
     * Builds the tree.
     */
    void buildTree();

    /**
     * Inserts a new element into the tree based on the specified code.
     *
     * @param code   The code determining the position of the new element.
     * @param letter The element to be inserted into the tree.
     * @return The reference to the updated tree.
     */
    LinkedConverterTreeInterface<T> insert(String code, T letter);

    /**
     * Adds a new node to the tree.
     *
     * @param root   The root of the current subtree.
     * @param code   The code determining the position of the new node.
     * @param letter The data for the new node.
     */
    void addNode(TreeNode<T> root, String code, T letter);

    /**
     * Fetches the data corresponding to the specified code.
     *
     * @param code The code used to locate the desired data.
     * @return The data corresponding to the code.
     */
    T fetch(String code);

    /**
     * Fetches the data from a specific node.
     *
     * @param root The root of the current subtree.
     * @param code The code used to locate the desired data.
     * @return The data corresponding to the code.
     */
    T fetchNode(TreeNode<T> root, String code);

    /**
     * Creates an ArrayList representation of the tree in LNR (In-Order) order.
     *
     * @return The ArrayList containing all elements in LNR order.
     */
    ArrayList<T> toArrayList();

    /**
     * Performs an LNR (In-Order) traversal and adds the data to a list.
     *
     * @param root The root of the current subtree.
     * @param list The list to store the traversal result.
     */
    void LNRoutputTraversal(TreeNode<T> root, ArrayList<T> list);

    /**
     * Unsupported operation for deleting an element from the tree.
     *
     * @param data The data to delete.
     * @return The reference to the updated tree.
     * @throws UnsupportedOperationException If the operation is not supported.
     */
    LinkedConverterTreeInterface<T> delete(T data) throws UnsupportedOperationException;

    /**
     * Unsupported operation for updating the tree.
     *
     * @return The reference to the updated tree.
     * @throws UnsupportedOperationException If the operation is not supported.
     */
    LinkedConverterTreeInterface<T> update() throws UnsupportedOperationException;
}
