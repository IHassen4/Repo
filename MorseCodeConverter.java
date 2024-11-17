import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Utility class for converting Morse code to English using a MorseCodeTree.
 */
public class MorseCodeConverter {
    
    // Static instance of the MorseCodeTree
    private static MorseCodeTree morseCodeTree = new MorseCodeTree();

    /**
     * Converts a Morse code string to English.
     *
     * @param code The Morse code string (e.g., "... --- ...").
     * @return The English translation of the Morse code.
     */
    public static String convertToEnglish(String code) {
        String[] words = code.split(" / "); // Split Morse code into words by " / "
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String[] letters = word.split(" "); // Split each word into letters by spaces
            for (String letter : letters) {
                result.append(morseCodeTree.fetch(letter)); // Convert each letter
            }
            result.append(" "); // Add a space between words
        }

        return result.toString().trim(); // Remove the trailing space
    }

    /**
     * Converts a file containing Morse code to English.
     *
     * @param file The file containing Morse code.
     * @return The English translation of the Morse code in the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public static String convertToEnglish(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StringBuilder fileContents = new StringBuilder();

        // Read the entire file content
        while (scanner.hasNextLine()) {
            fileContents.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        // Use the first method to convert the content to English
        return convertToEnglish(fileContents.toString().trim());
    }

    /**
     * Prints the MorseCodeTree in LNR (in-order) traversal.
     *
     * @return The in-order traversal of the tree as a string.
     */
    public static String printTree() {
        return String.join(" ", morseCodeTree.toArrayList());
    }
}
