import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TownGraphManager implements TownGraphManagerInterface {
    private Graph<Town, Road> graph;

    public TownGraphManager() {
        graph = new Graph<>();
    }

    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);
        if (t1 == null || t2 == null) return false;
        graph.addEdge(t1, t2, weight, roadName);
        return true;
    }

    @Override
    public String getRoad(String town1, String town2) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);
        Road road = graph.getEdge(t1, t2);
        return road != null ? road.getName() : null;
    }

    @Override
    public boolean addTown(String v) {
        Town town = new Town(v);
        return graph.addVertex(town);
    }

    @Override
    public Town getTown(String name) {
        for (Town town : graph.vertexSet()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        return null;
    }

    @Override
    public boolean containsTown(String v) {
        Town town = new Town(v);
        return graph.containsVertex(town);
    }

    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);
        return graph.containsEdge(t1, t2);
    }

    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> roads = new ArrayList<>();
        for (Road road : graph.edgeSet()) {
            roads.add(road.getName());
        }
        Collections.sort(roads);
        return roads;
    }

    @Override
    public boolean deleteRoadConnection(String town1, String town2, String roadName) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);
        Road road = graph.getEdge(t1, t2);
        if (road != null && road.getName().equals(roadName)) {
            graph.removeEdge(t1, t2, road.getWeight(), roadName);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTown(String v) {
        Town town = getTown(v);
        if (town != null) {
            return graph.removeVertex(town);
        }
        return false;
    }

    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> towns = new ArrayList<>();
        for (Town town : graph.vertexSet()) {
            towns.add(town.getName());
        }
        Collections.sort(towns);
        return towns;
    }

    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);

        if (t1 == null || t2 == null) {
            return new ArrayList<>(); // Return empty list if towns are invalid
        }

        ArrayList<String> path = graph.shortestPath(t1, t2);
        return path != null ? path : new ArrayList<>(); // Return empty list if no path exists
    }

    
    public void populateTownGraph(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(","); // Assuming CSV format: Town1,Town2,Weight,RoadName

            if (parts.length == 4) {
                String town1 = parts[0].trim();
                String town2 = parts[1].trim();
                int weight = Integer.parseInt(parts[2].trim());
                String roadName = parts[3].trim();

                // Add towns and road
                addTown(town1);
                addTown(town2);
                addRoad(town1, town2, weight, roadName);
            }
        }

        scanner.close();
    }
}
