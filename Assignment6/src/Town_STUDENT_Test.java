import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
    private Town town1, town2, town3;

    @Before
    public void setUp() {
        town1 = new Town("Town_A");
        town2 = new Town("Town_B");
        town3 = new Town("Town_A");
    }

    @Test
    public void testGetName() {
        assertEquals("Town_A", town1.getName());
    }

    @Test
    public void testEquals() {
        assertTrue(town1.equals(town3)); // Same name
        assertFalse(town1.equals(town2));
    }

    @Test
    public void testCompareTo() {
        assertTrue(town1.compareTo(town2) < 0);
        assertTrue(town2.compareTo(town1) > 0);
        assertEquals(0, town1.compareTo(town3));
    }

    @Test
    public void testHashCode() {
        assertEquals(town1.hashCode(), town3.hashCode());
        assertNotEquals(town1.hashCode(), town2.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("Town_A", town1.toString());
    }
}
