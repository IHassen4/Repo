
/**
 * Generic TreeNode class for a binary tree.
 *
 * @param <T> The type of data stored in the node.
 */
public class TreeNode<T> {
    protected T data; // The data stored in the node
    protected TreeNode<T> left; // Reference to the left child
    protected TreeNode<T> right; // Reference to the right child

    /**
     * Constructor to create a new TreeNode with the given data.
     *
     * @param data The data to be stored in the node.
     */
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * Copy constructor to create a new TreeNode as a copy of another node.
     *
     * @param node The TreeNode to copy.
     */
   

}
