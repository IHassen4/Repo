import java.util.*;

// Node class for the Huffman Tree
class Node implements Comparable<Node> {
    char character;
    int frequency;
    Node left;
    Node right;

    // Constructor
    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // Compare nodes based on frequency, with tie-breaking rules for alphabetic order
    @Override
    public int compareTo(Node other) {
        if (this.frequency == other.frequency) {
            return Character.compare(this.character, other.character);
        }
        return this.frequency - other.frequency;
    }
}

public class HuffmanTree {

    // Method to build the Huffman Tree
    public static Node buildHuffmanTree(String input) {
        // Count frequencies of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : input.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        // Create a priority queue and populate it with nodes
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Build the tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            // Combine nodes into a new parent node
            Node parent = new Node('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            pq.add(parent);
        }

        // Return the root of the tree
        return pq.poll();
    }

    // Method to generate codes for each character
    public static void generateCodes(Node root, String code, Map<Character, String> codes) {
        if (root == null) {
            return;
        }

        // If leaf node, store the code
        if (root.left == null && root.right == null) {
            codes.put(root.character, code);
        }

        generateCodes(root.left, code + "0", codes);
        generateCodes(root.right, code + "1", codes);
    }

    // Method to encode a string into bits
    public static String encode(String input, Map<Character, String> codes) {
        StringBuilder encodedString = new StringBuilder();
        for (char ch : input.toCharArray()) {
            encodedString.append(codes.get(ch));
        }
        return encodedString.toString();
    }

    // Method to decode a string of bits using the Huffman Tree
    public static String decode(String encodedString, Node root) {
        StringBuilder decodedString = new StringBuilder();
        Node current = root;
        for (char bit : encodedString.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            // If leaf node, append the character to the decoded string
            if (current.left == null && current.right == null) {
                decodedString.append(current.character);
                current = root;
            }
        }
        return decodedString.toString();
    }

    // Main method
    public static void main(String[] args) {
        // Example input string (replace with actual input)
        String input = "example input string"; // Replace with your input string

        // Step 1: Build the Huffman Tree
        Node root = buildHuffmanTree(input);

        // Step 2: Generate Huffman Codes
        Map<Character, String> codes = new HashMap<>();
        generateCodes(root, "", codes);

        // Step 3: Print the Huffman Codes
        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Step 4: Encode the input string
        String encodedString = encode(input, codes);
        System.out.println("Encoded String: " + encodedString);

        // Step 5: Decode the encoded string
        String decodedString = decode(encodedString, root);
        System.out.println("Decoded String: " + decodedString);

        // Validation
        if (input.equals(decodedString)) {
            System.out.println("Decoding is correct!");
        } else {
            System.out.println("Decoding is incorrect.");
        }
    }
}
