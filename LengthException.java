
public class LengthException extends Throwable{

	private static final long serialVersionUID = 1L;
	
	private String message;
	public LengthException() {
		
		message = "The password must be at least 6 characters long";
	}
	
	public String getMessage() {
		return message;
	}

}
