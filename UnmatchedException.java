
public class UnmatchedException extends Throwable{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UnmatchedException() {
		message = "Passwords do not match";
	}
	
	public  String getMessage() {
		return message;
	}
	

}
