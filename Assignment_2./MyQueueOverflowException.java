
public class MyQueueOverflowException extends Throwable {

	private static final long serialVersionUID = 1L;

	public MyQueueOverflowException() {
		super("Queue overflow exception found.");
	}

}
