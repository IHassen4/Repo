import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;

public class MorseCodeConverterTest {

    @Test
    public void testConvertToEnglishString() {
        // Testing conversion of a Morse code string to English
        String morseCode = "... --- ... / .... . .-.. .-.. ---";
        String expected = "sos hello";
        assertEquals(expected, MorseCodeConverter.convertToEnglish(morseCode));
    }

    @Test
    public void testConvertToEnglishFile() throws IOException {
        // Create a temporary file with Morse code content
        File tempFile = File.createTempFile("morseCodeTest", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(".... . .-.. .-.. --- / .-- --- .-. .-.. -..");
        }

        // Expected English translation
        String expected = "hello world";
        assertEquals(expected, MorseCodeConverter.convertToEnglish(tempFile));

        // Clean up the temporary file
        tempFile.deleteOnExit();
    }

    @Test
    public void testInvalidMorseCode() {
        // Testing an invalid Morse code input
        String invalidMorseCode = "... --- ... ...---";
        assertThrows(IllegalArgumentException.class, () -> MorseCodeConverter.convertToEnglish(invalidMorseCode));
    }
}
