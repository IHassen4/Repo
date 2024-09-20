import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordCheckerTest_STUDENT {

    private ArrayList<String> passwords;

    @Before
    public void setUp() throws Exception {
        passwords = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        passwords.clear(); // Clear the list after each test
        passwords = null;  // Set the list to null to aid garbage collection
    }

    @Test
    public void testIsValidPasswordTooShort() throws NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
        try {
            PasswordCheckerUtility.isValidPassword​("abc12");
            fail("Expected LengthException not thrown");
        } catch (LengthException e) {
            assertTrue("Successfully threw a LengthException", true);
        } catch (Exception e) {
            fail("Threw an unexpected exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testIsValidPasswordNoUpperAlpha() throws LengthException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
        try {
            PasswordCheckerUtility.isValidPassword​("abcde1!");
            fail("Expected NoUpperAlphaException not thrown");
        } catch (NoUpperAlphaException e) {
            assertTrue("Successfully threw a NoUpperAlphaException", true);
        } catch (Exception e) {
            fail("Threw an unexpected exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testIsValidPasswordNoLowerAlpha() throws LengthException, NoUpperAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
        try {
            PasswordCheckerUtility.isValidPassword​("ABCDE1!");
            fail("Expected NoLowerAlphaException not thrown");
        } catch (NoLowerAlphaException e) {
            assertTrue("Successfully threw a NoLowerAlphaException", true);
        } catch (Exception e) {
            fail("Threw an unexpected exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testIsWeakPassword() throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException {
        try {
            PasswordCheckerUtility.isValidPassword​("AAA12!a");
            fail("Expected InvalidSequenceException not thrown");
        } catch (InvalidSequenceException e) {
            assertTrue("Successfully threw an InvalidSequenceException", true);
        } catch (Exception e) {
            fail("Threw an unexpected exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testIsValidPasswordInvalidSequence() throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException {
        try {
            PasswordCheckerUtility.isValidPassword​("abcAB1!");
            assertTrue("Successfully passed password check", true);
        } catch (InvalidSequenceException e) {
            fail("Did not expect an InvalidSequenceException: " + e.getMessage());
        } catch (Exception e) {
            fail("Threw an unexpected exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testIsValidPasswordNoDigit() throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoSpecialCharacterException, InvalidSequenceException {
        try {
            PasswordCheckerUtility.isValidPassword​("Abcdef@!");
            fail("Expected NoDigitException not thrown");
        } catch (NoDigitException e) {
            assertTrue("Successfully threw a NoDigitException", true);
        } catch (Exception e) {
            fail("Threw an unexpected exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testIsValidPasswordSuccessful() throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
        try {
            boolean result = PasswordCheckerUtility.isValidPassword​("Valid1!");
            assertTrue("Password should be valid", result);
        } catch (Exception e) {
            fail("Did not expect any exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testInvalidPasswords() {
        passwords.add("short");
        passwords.add("noUpper1!");
        passwords.add("NOLOWER1!");
        passwords.add("NoDigit!");
        passwords.add("InvalidSequenceAAA1!");
        
        ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords​(passwords);
        
        assertTrue("Invalid passwords list should not be empty", !invalidPasswords.isEmpty());
        assertTrue("Invalid passwords should contain the word 'short'", invalidPasswords.get(0).contains("short"));
    }
}
