
public class NoSpecialCharacterException extends Throwable{

	
private static final long serialVersionUID = 1L;
	
	private String message;
	
	public NoSpecialCharacterException() {
		message = "The password must contain at least one special character";
	}

	public String getMessage() {
		return message;
	}
}
