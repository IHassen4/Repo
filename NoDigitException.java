
public class NoDigitException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	String message;
	
	public NoDigitException(){
		message = "The password must contain at least one digit";
	}
	
	public String getMessage() {
		return message;
	}
}
