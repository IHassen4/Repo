import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyQueueTest {
    public MyQueue<String> stringQ;
    public String a="a", b="b", c="c", d="d", e="e", f="f";
    public ArrayList<String> fill = new ArrayList<String>();
    
    // STUDENT: student tests will use the doubleQ
    public MyQueue<Double> doubleQ;
    // Additional variables for your student tests
    public Double val1 = 1.0, val2 = 2.0, val3 = 3.0, val4 = 4.0, val5 = 5.0, val6 = 6.0;
    public ArrayList<Double> fillList = new ArrayList<Double>();
    
    @BeforeEach
    public void setUp1() throws Exception, MyQueueOverflowException {
        // Initialize doubleQ with a size of 5 for queue tests
        doubleQ = new MyQueue<Double>(5);
        doubleQ.enqueue(val1);
        doubleQ.enqueue(val2);
        doubleQ.enqueue(val3);
        
        // Initialize fillList for use in the fill test
        fillList.add(4.0);
        fillList.add(5.0);
        fillList.add(6.0);
    }

    @AfterEach
    public void tearDown1() throws Exception {
        doubleQ = null;
    }

    @BeforeEach
    public void setUp() throws Exception, MyQueueOverflowException {
        stringQ = new MyQueue<String>(5);
        stringQ.enqueue(a);
        stringQ.enqueue(b);
        stringQ.enqueue(c);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringQ = null;
        doubleQ = null;
    }

    @Test
    public void testIsEmpty() throws MyQueueUnderflowException {
        assertEquals(false, stringQ.isEmpty());
        stringQ.dequeue();
        stringQ.dequeue();
        stringQ.dequeue();
        assertEquals(true, stringQ.isEmpty());
    }

    @Test
    public void testDequeue() {
        try {
            assertEquals(a, stringQ.dequeue());
            assertEquals(b, stringQ.dequeue());
            assertEquals(c, stringQ.dequeue());
            // Queue is empty, next statement should cause QueueUnderFlowException
            stringQ.dequeue();
            assertTrue("This should have caused a QueueUnderflowException", false);
        }
        catch (MyQueueUnderflowException e) {
            assertTrue("This should have caused a QueueUnderflowException", true);
        }
        catch (Exception e) {
            assertTrue("This should have caused a QueueUnderflowException", false);
        }
    }

    @Test
    public void testDequeueStudent() {
        try {
            // Ensure the elements are dequeued in the correct order
            assertEquals(Double.valueOf(1.0), doubleQ.dequeue());
            assertEquals(Double.valueOf(2.0), doubleQ.dequeue());
            assertEquals(Double.valueOf(3.0), doubleQ.dequeue());

            // The queue is now empty; the next statement should cause a MyQueueUnderflowException
            doubleQ.dequeue(); // This should throw MyQueueUnderflowException
            assertTrue("This should have caused a MyQueueUnderflowException", false);
        } catch (MyQueueUnderflowException e) {
            assertTrue("This should have caused a MyQueueUnderflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a MyQueueUnderflowException", false);
        }
    }


    @Test
    public void testSize() throws MyQueueOverflowException, MyQueueUnderflowException {
        assertEquals(3, stringQ.size());
        stringQ.enqueue(d);
        assertEquals(4, stringQ.size());
        stringQ.dequeue();
        stringQ.dequeue();
        assertEquals(2, stringQ.size());
    }

    @Test
    public void testEnqueue() {
        try {
            assertEquals(3, stringQ.size());
            assertEquals(true, stringQ.enqueue(d));
            assertEquals(4, stringQ.size());
            assertEquals(true, stringQ.enqueue(e));
            assertEquals(5, stringQ.size());
            // Queue is full, next statement should cause QueueOverflowException
            stringQ.enqueue(f);
            assertTrue("This should have caused a QueueOverflowException", false);
        }
        catch (MyQueueOverflowException e) {
            assertTrue("This should have caused a QueueOverflowException", true);
        }
        catch (Exception e) {
            assertTrue("This should have caused a QueueOverflowException", false);
        }
    }

    @Test
    public void testEnqueueStudent() throws MyQueueOverflowException {
        // Test enqueuing with doubleQ
        assertEquals(3, doubleQ.size());
        assertTrue(doubleQ.enqueue(val4));
        assertEquals(4, doubleQ.size());
        assertTrue(doubleQ.enqueue(val5));
        assertEquals(5, doubleQ.size());
        try {
            // This should cause a QueueOverflowException since the queue is now full
            doubleQ.enqueue(val6);
            assertTrue("This should have caused a QueueOverflowException", false);
        } catch (MyQueueOverflowException e) {
            assertTrue("This should have caused a QueueOverflowException", true);
        }
    }

    @Test
    public void testIsFull() throws MyQueueOverflowException {
        assertEquals(false, stringQ.isFull());
        stringQ.enqueue(d);
        stringQ.enqueue(e);
        assertEquals(true, stringQ.isFull());
    }

    @Test
    public void testToString() throws MyQueueOverflowException {
        assertEquals("abc", stringQ.toString());
        stringQ.enqueue(d);
        assertEquals("abcd", stringQ.toString());
        stringQ.enqueue(e);
        assertEquals("abcde", stringQ.toString());
    }

    @Test
    public void testToStringStudent() {
        // Test the toString method with doubleQ
        assertEquals("1.02.03.0", doubleQ.toString());
        try {
            doubleQ.enqueue(val4);
            assertEquals("1.02.03.04.0", doubleQ.toString());
        } catch (MyQueueOverflowException e) {
            fail("The queue should not be full at this point.");
        }
    }

    @Test
    public void testToStringDelimiter() throws MyQueueOverflowException {
        assertEquals("a%b%c", stringQ.toString("%"));
        stringQ.enqueue(d);
        assertEquals("a&b&c&d", stringQ.toString("&"));
        stringQ.enqueue(e);
        assertEquals("a/b/c/d/e", stringQ.toString("/"));
    }

    @Test
    public void testFill() throws MyQueueOverflowException, MyQueueUnderflowException {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        // Start with an empty queue
        stringQ = new MyQueue<String>(5);
        // Fill with an ArrayList
        stringQ.fill(fill);
        assertEquals(3, stringQ.size());
        assertEquals("apple", stringQ.dequeue());
        assertEquals("banana", stringQ.dequeue());
        assertEquals("carrot", stringQ.dequeue());        
    }
}
