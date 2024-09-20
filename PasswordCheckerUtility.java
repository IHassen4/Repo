import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordCheckerUtility extends java.lang.Object {

	public PasswordCheckerUtility() {

	}

	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {

		if (!password.equals(passwordConfirm)) {

			throw new UnmatchedException();

		}

	}

	public static boolean comparePasswordsWithReturn​(String password, String passwordConfirm) {

		if (password.equals(passwordConfirm)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isValidLength​(String password) throws LengthException {

		if (password.length() < 6) {
			throw new LengthException();
		}

		return true;
	}

	public static boolean hasUpperAlpha​(String password) throws NoUpperAlphaException {

		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		throw new NoUpperAlphaException();

	}

	public static boolean hasLowerAlpha​(String password) throws NoLowerAlphaException {

		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				return true;
			}
		}
		throw new NoLowerAlphaException();

	}

	public static boolean hasDigit​(String password) throws NoDigitException {

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isDigit(c)) {
				return true; // Found a digit
			}
		}

		throw new NoDigitException();
	}

	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
	    // Define the regular expression pattern to match any special character
	    Pattern pattern = Pattern.compile(".*[^a-zA-Z0-9].*");
	    Matcher matcher = pattern.matcher(password);
	    
	    // Check if the password contains at least one special character
	    if (matcher.find()) {
	        return true; // Password contains a special character
	    } else {
	        throw new NoSpecialCharacterException();
	    }
	}
	public static boolean NoSameCharInSequence​(String password) throws InvalidSequenceException {
		int count = 1;
		for (int i = 1; i < password.length(); i++) {
			if (password.charAt(i) == password.charAt(i - 1)) {
				count++;
				if (count > 2) {

					throw new InvalidSequenceException();
				}
			} else {
				count = 1;
			}
		}

		return true;

	}

	public static boolean isValidPassword​(String password) throws LengthException, NoUpperAlphaException,
			NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		if (!isValidLength​(password)) {

			throw new LengthException();

		}

		if (!hasUpperAlpha​(password)) {

			throw new NoUpperAlphaException();

		}
		if (!hasLowerAlpha​(password)) {

			throw new NoLowerAlphaException();

		}
		if (!hasDigit​(password)) {

			throw new NoDigitException();

		}
		if (!hasSpecialChar(password)) {

			throw new NoSpecialCharacterException();

		}
		if (!NoSameCharInSequence​(password)) {

			throw new InvalidSequenceException();
		}

		return true;
	}

	public static boolean hasBetweenSixAndNineChars​(String password) {

		if (password.length() >= 6 && password.length() <= 9) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isWeakPassword​(String password)
			throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException,
			NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		if (isValidPassword​(password) && hasBetweenSixAndNineChars​(password)) {

			throw new WeakPasswordException();
		}

		return false;
	}

	public static ArrayList<String> getInvalidPasswords​(ArrayList<String> passwords) {

		ArrayList<String> invalidPasswords = new ArrayList<>();

		for (String password : passwords) {
			try {

				isValidPassword​(password);
			} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
					| NoSpecialCharacterException | InvalidSequenceException e) {
				String entry = password + " " + e.getMessage();
				invalidPasswords.add(entry);
			}

		}
		return invalidPasswords;
	}

}
