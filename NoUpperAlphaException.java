
public class NoUpperAlphaException extends Throwable{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public NoUpperAlphaException() {
		message = "The password must contain at least one uppercase alphabetic character\n";
	}

	public String getMessage() {
		return message;
	}
}
