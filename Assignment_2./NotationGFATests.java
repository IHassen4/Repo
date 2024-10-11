import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotationGFATests {
	public MyQueue<String> stringQ;
	public MyStack<String> stringS;
	
	@BeforeEach
	void setUp() throws Exception, MyQueueOverflowException {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue("a");
		stringS = new MyStack<String>(2);
		stringS.push("a");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	 
	@Test
	public void testIsEmptyQueue() throws MyQueueUnderflowException {
		assertEquals(false,stringQ.isEmpty());
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());
	}
	@Test
	public void testDequeue() {
		try {
			assertEquals("a", stringQ.dequeue());
			 
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (MyQueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}
	
	@Test
	public void testIsEmptyStack() throws MyStackUnderflowException {
		assertEquals(false,stringS.isEmpty());
		stringS.pop();
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() throws MyStackOverflowException {
		assertEquals(false, stringS.isFull());
		stringS.push("b");
		assertEquals(true, stringS.isFull());
	}


}
