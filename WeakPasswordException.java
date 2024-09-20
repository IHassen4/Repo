
public class WeakPasswordException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public WeakPasswordException() {
		
		message = "The password is OK but weak - it contains fewer than 10 characters.";

	}
	
	public String getMessage() {
		return message;
	}
	
}
