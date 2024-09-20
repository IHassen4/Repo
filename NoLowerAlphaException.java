
public class NoLowerAlphaException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public NoLowerAlphaException() {
		
		message = "The password must contain at least one lowercase alphabetic character";

	}
	
	public String getMessage() {
		return message;
	}
}
