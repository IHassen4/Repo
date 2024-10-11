import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTest {
    public MyStack<String> stringS;
    public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
    public ArrayList<String> fill = new ArrayList<String>();

    // STUDENT: student tests will use the doubleS
    public MyStack<Double> doubleS;
    public Double val1 = 1.0, val2 = 2.0, val3 = 3.0, val4 = 4.0, val5 = 5.0, val6 = 6.0;

    @BeforeEach
    public void setUp() throws Exception {
        stringS = new MyStack<String>(5);
        stringS.push(a);
        stringS.push(b);
        stringS.push(c);

        // STUDENT: add setup for doubleS for student tests
        doubleS = new MyStack<Double>(5);
        doubleS.push(val1);
        doubleS.push(val2);
        doubleS.push(val3);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringS = null;
        doubleS = null;
    }

    @Test
    public void testIsEmpty() throws MyStackUnderflowException {
        assertEquals(false, stringS.isEmpty());
        stringS.pop();
        stringS.pop();
        stringS.pop();
        assertEquals(true, stringS.isEmpty());
    }

    @Test
    public void testIsFull() throws MyStackOverflowException {
        assertEquals(false, stringS.isFull());
        stringS.push(d);
        stringS.push(e);
        assertEquals(true, stringS.isFull());
    }

    @Test
    public void testPop() {
        try {
            assertEquals(c, stringS.pop());
            assertEquals(b, stringS.pop());
            assertEquals(a, stringS.pop());
            // Stack is empty, next statement should cause StackUnderflowException
            stringS.pop();
            assertTrue("This should have caused a StackUnderflowException", false);
        } catch (MyStackUnderflowException e) {
            assertTrue("This should have caused a StackUnderflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a StackUnderflowException", false);
        }
    }

    @Test
    public void testPopStudent() {
        try {
            assertEquals(Double.valueOf(3.0), doubleS.pop());
            assertEquals(Double.valueOf(2.0), doubleS.pop());
            assertEquals(Double.valueOf(1.0), doubleS.pop());
            // The stack is empty; the next statement should cause a StackUnderflowException
            doubleS.pop();
            assertTrue("This should have caused a StackUnderflowException", false);
        } catch (MyStackUnderflowException e) {
            assertTrue("This should have caused a StackUnderflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a StackUnderflowException", false);
        }
    }

    @Test
    public void testTop() throws MyStackOverflowException, MyStackUnderflowException {
        assertEquals(c, stringS.top());
        stringS.push(d);
        assertEquals(d, stringS.top());
        stringS.pop();
        stringS.pop();
        assertEquals(b, stringS.top());
    }

    @Test
    public void testSize() throws MyStackOverflowException, MyStackUnderflowException {
        assertEquals(3, stringS.size());
        stringS.push(d);
        assertEquals(4, stringS.size());
        stringS.pop();
        stringS.pop();
        assertEquals(2, stringS.size());
    }

    @Test
    public void testPush() {
        try {
            assertEquals(3, stringS.size());
            assertEquals(true, stringS.push(d));
            assertEquals(4, stringS.size());
            assertEquals(true, stringS.push(e));
            assertEquals(5, stringS.size());
            // Stack is full, next statement should cause StackOverflowException
            stringS.push(f);
            assertTrue("This should have caused a StackOverflowException", false);
        } catch (MyStackOverflowException e) {
            assertTrue("This should have caused a StackOverflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a StackOverflowException", false);
        }
    }

    @Test
    public void testPushStudent() {
        try {
            assertEquals(3, doubleS.size());
            assertTrue(doubleS.push(val4));
            assertEquals(4, doubleS.size());
            assertTrue(doubleS.push(val5));
            assertEquals(5, doubleS.size());
            // Stack is full, next statement should cause StackOverflowException
            doubleS.push(val6);
            assertTrue("This should have caused a StackOverflowException", false);
        } catch (MyStackOverflowException e) {
            assertTrue("This should have caused a StackOverflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a StackOverflowException", false);
        }
    }

    @Test
    public void testToString() throws MyStackOverflowException {
        assertEquals("abc", stringS.toString());
        stringS.push(d);
        assertEquals("abcd", stringS.toString());
        stringS.push(e);
        assertEquals("abcde", stringS.toString());
    }

    @Test
    public void testToStringStudent() {
        assertEquals("1.02.03.0", doubleS.toString());
        try {
            doubleS.push(val4);
            assertEquals("1.02.03.04.0", doubleS.toString());
        } catch (MyStackOverflowException e) {
            fail("The stack should not be full at this point.");
        }
    }

    @Test
    public void testToStringDelimiter() throws MyStackOverflowException {
        assertEquals("a%b%c", stringS.toString("%"));
        stringS.push(d);
        assertEquals("a&b&c&d", stringS.toString("&"));
        stringS.push(e);
        assertEquals("a/b/c/d/e", stringS.toString("/"));
    }

    @Test
    public void testFill() throws MyStackOverflowException, MyStackUnderflowException {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        // Start with an empty stack
        stringS = new MyStack<String>(5);
        // Fill with an ArrayList
        stringS.fill(fill);
        assertEquals(3, stringS.size());
        assertEquals("carrot", stringS.pop());
        assertEquals("banana", stringS.pop());
        assertEquals("apple", stringS.pop());
    }
}
