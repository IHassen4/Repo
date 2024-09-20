
public class InvalidSequenceException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public InvalidSequenceException() {
		message = "The password cannot contain more than two of the same character in sequence.";
	}

	public String getMessage() {
		return message;
	}

}
