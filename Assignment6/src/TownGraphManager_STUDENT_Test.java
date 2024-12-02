import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TownGraphManager_STUDENT_Test {
    private TownGraphManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new TownGraphManager();
        manager.addTown("Town_A");
        manager.addTown("Town_B");
        manager.addTown("Town_C");
        manager.addRoad("Town_A", "Town_B", 3, "Road_1");
        manager.addRoad("Town_B", "Town_C", 5, "Road_2");
    }

    @Test
    public void testAddTown() {
        assertTrue(manager.addTown("Town_D"));
        assertFalse(manager.addTown("Town_A")); // Duplicate
    }

    @Test
    public void testAddRoad() {
        assertTrue(manager.addRoad("Town_C", "Town_A", 4, "Road_3"));
        assertFalse(manager.addRoad("Town_X", "Town_Y", 2, "Road_4")); // Towns do not exist
    }

    @Test
    public void testGetPath() {
        ArrayList<String> path = manager.getPath("Town_A", "Town_C");
        assertNotNull(path);
        assertEquals("Town_A via Road_1 to Town_B 3 mi", path.get(0).trim());
        assertEquals("Town_B via Road_2 to Town_C 5 mi", path.get(1).trim());
    }

    @Test
    public void testContainsTown() {
        assertTrue(manager.containsTown("Town_A"));
        assertFalse(manager.containsTown("Town_X"));
    }

    @Test
    public void testDeleteRoadConnection() {
        assertTrue(manager.deleteRoadConnection("Town_A", "Town_B", "Road_1"));
        assertFalse(manager.containsRoadConnection("Town_A", "Town_B"));
    }
}
