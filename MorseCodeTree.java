import java.util.ArrayList;

/**
 * MorseCodeTree class that represents a binary tree for Morse code translations.
 * It implements the LinkedConverterTreeInterface.
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
    private TreeNode<String> root; // Root node of the tree

    /**
     * Default constructor.
     * It initializes the root and builds the tree.
     */
    public MorseCodeTree() {
        root = new TreeNode<>(""); // Root initialized with an empty string
        buildTree();
    }

    /**
     * Builds the Morse code tree with letters and their corresponding codes.
     */

    @Override
    public void buildTree() {
        // Level 1
        insert(".", "e");
        insert("-", "t");
        // Level 2
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");
        // Level 3
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");
        // Level 4
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
        insert("-.-.", "c");
// Added the missing "q"
        // Digits
        
    }


    /**
     * Inserts a letter into the tree based on the given Morse code.
     *
     * @param code   The Morse code representing the letter.
     * @param letter The letter to insert.
     * @return Reference to the current MorseCodeTree object.
     */
    @Override
    public MorseCodeTree insert(String code, String letter) {
        addNode(root, code, letter); // Start at the root
        return this;
    }

    /**
     * Recursively adds a node to the tree.
     *
     * @param current The current node.
     * @param code    The remaining Morse code.
     * @param letter  The letter to add.
     */
    @Override
    public void addNode(TreeNode<String> current, String code, String letter) {
        if (code.length() == 1) {
            // If the code is a single character, add the letter to the left or right
            if (code.equals(".")) {
                current.left = new TreeNode<>(letter);
            } else if (code.equals("-")) {
                current.right = new TreeNode<>(letter);
            }
        } else {
            // Otherwise, continue traversing the tree
            if (code.charAt(0) == '.') {
                if (current.left == null) {
                    current.left = new TreeNode<>("");
                }
                addNode(current.left, code.substring(1), letter);
            } else if (code.charAt(0) == '-') {
                if (current.right == null) {
                    current.right = new TreeNode<>("");
                }
                addNode(current.right, code.substring(1), letter);
            }
        }
    }

    /**
     * Fetches the letter corresponding to the given Morse code.
     *
     * @param code The Morse code.
     * @return The corresponding letter.
     */
    @Override
    public String fetch(String code) {
        return fetchNode(root, code);
    }

    /**
     * Recursively fetches the letter for the given Morse code.
     *
     * @param current The current node.
     * @param code    The remaining Morse code.
     * @return The corresponding letter.
     */
    @Override
    public String fetchNode(TreeNode<String> current, String code) {
        if (current == null) {
            throw new IllegalArgumentException("Invalid Morse code: Code leads to a null node.");
        }

        if (code.length() == 1) {
            if (code.equals(".")) {
                if (current.left != null && current.left.data != null) {
                    return current.left.data;
                }
                throw new IllegalArgumentException("Invalid Morse code: No data for '.' at this position.");
            } else if (code.equals("-")) {
                if (current.right != null && current.right.data != null) {
                    return current.right.data;
                }
                throw new IllegalArgumentException("Invalid Morse code: No data for '-' at this position.");
            }
        } else {
            if (code.charAt(0) == '.') {
                return fetchNode(current.left, code.substring(1));
            } else if (code.charAt(0) == '-') {
                return fetchNode(current.right, code.substring(1));
            }
        }

        throw new IllegalArgumentException("Invalid Morse code input.");
    }

    /**
     * Returns the tree as an ArrayList in LNR (in-order) traversal.
     *
     * @return An ArrayList containing the letters in LNR order.
     */
    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        LNRoutputTraversal(root, list);
        return list;
    }

    /**
     * Performs an in-order traversal to add elements to the list.
     *
     * @param current The current node.
     * @param list    The list to add the elements to.
     */
    @Override
    public void LNRoutputTraversal(TreeNode<String> current, ArrayList<String> list) {
        if (current != null) {
            LNRoutputTraversal(current.left, list); // Traverse left
            if (!current.data.isEmpty()) {
                list.add(current.data); // Add data
            }
            LNRoutputTraversal(current.right, list); // Traverse right
        }
    }

    /**
     * Unsupported delete method.
     */
    @Override
    public MorseCodeTree delete(String data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }

    /**
     * Unsupported update method.
     */
    @Override
    public MorseCodeTree update() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Update operation is not supported.");
    }
}
