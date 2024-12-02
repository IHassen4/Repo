import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

public class Graph_STUDENT_Test {
    private Graph<Town, Road> graph;
    private Town town1, town2, town3;

    @Before
    public void setUp() {
        graph = new Graph<>();
        town1 = new Town("Town_A");
        town2 = new Town("Town_B");
        town3 = new Town("Town_C");

        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addVertex(town3);

        graph.addEdge(town1, town2, 5, "Road_1");
        graph.addEdge(town2, town3, 7, "Road_2");
    }

    @Test
    public void testAddEdge() {
        Road road = graph.addEdge(town1, town3, 10, "Road_3");
        assertNotNull(road);
        assertEquals(10, road.getWeight());
        assertEquals("Road_3", road.getName());
    }

    @Test
    public void testGetEdge() {
        Road road = graph.getEdge(town1, town2);
        assertNotNull(road);
        assertEquals("Road_1", road.getName());
    }

    @Test
    public void testVertexSet() {
        Set<Town> vertices = graph.vertexSet();
        assertEquals(3, vertices.size());
    }

    @Test
    public void testRemoveEdge() {
        graph.removeEdge(town1, town2, 5, "Road_1");
        assertNull(graph.getEdge(town1, town2));
    }

    @Test
    public void testShortestPath() {
        ArrayList<String> path = graph.shortestPath(town1, town3);
        assertNotNull(path);
        assertEquals("Town_A via Road_1 to Town_B 5 mi", path.get(0).trim());
        assertEquals("Town_B via Road_2 to Town_C 7 mi", path.get(1).trim());
    }
}
