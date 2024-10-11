
public class MyQueueUnderflowException extends Throwable{

	private static final long serialVersionUID = 1L;

	public  MyQueueUnderflowException() {
		super("Queue underflow exception found.");
	}

}
