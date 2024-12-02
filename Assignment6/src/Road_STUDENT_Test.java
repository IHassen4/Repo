import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
    private Road road1, road2;

    @Before
    public void setUp() {
        Town townA = new Town("Town_A");
        Town townB = new Town("Town_B");
        road1 = new Road(townA, townB, 10, "Road_1");
        road2 = new Road(townA, townB, 10, "Road_1");
    }

    @Test
    public void testGetName() {
        assertEquals("Road_1", road1.getName());
    }

    @Test
    public void testEquals() {
        assertTrue(road1.equals(road2));
    }

    @Test
    public void testContains() {
        Town townC = new Town("Town_C");
        assertTrue(road1.contains(new Town("Town_A")));
        assertFalse(road1.contains(townC));
    }

    @Test
    public void testGetWeight() {
        assertEquals(10, road1.getWeight());
    }
}
