import java.util.*;

public class Graph<V,E> implements GraphInterface<Town, Road> {
	
	private Map<Town, Town> previousVertexMap;
	private Map<Town, Integer> distanceMap;
    private Map<Town, Integer> townIndexMap; // Maps towns to their index in the adjacency matrix
    private List<Town> towns; // List of towns
    private int[][] adjacencyMatrix; // Adjacency matrix for road weights
    private List<Road> roads; // List of roads

    public Graph() {
        townIndexMap = new HashMap<>();
        towns = new ArrayList<>();
        roads = new ArrayList<>();
        adjacencyMatrix = new int[0][0]; // Initialize an empty matrix
    }


    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null || !containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
            return null;
        }
        for (Road road : roads) {
            // Match the road by source, destination, and weight
            if ((road.getSource().equals(sourceVertex) && road.getDestination().equals(destinationVertex)) ||
                (road.getSource().equals(destinationVertex) && road.getDestination().equals(sourceVertex))) {
                return road;
            }
        }
        return null;
    }


    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (sourceVertex == null || destinationVertex == null) throw new NullPointerException();
        if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) throw new IllegalArgumentException();

        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        roads.add(road);
        int sourceIndex = townIndexMap.get(sourceVertex);
        int destIndex = townIndexMap.get(destinationVertex);
        adjacencyMatrix[sourceIndex][destIndex] = weight;
        adjacencyMatrix[destIndex][sourceIndex] = weight; // For undirected graph
        return road;
    }

    @Override
    public boolean addVertex(Town v) {
        if (v == null) throw new NullPointerException();
        if (containsVertex(v)) return false;
        towns.add(v);
        townIndexMap.put(v, towns.size() - 1);

        // Resize adjacency matrix
        int newSize = towns.size();
        int[][] newMatrix = new int[newSize][newSize];
        for (int i = 0; i < newSize - 1; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, newSize - 1);
        }
        adjacencyMatrix = newMatrix;

        return true;
    }

    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        return getEdge(sourceVertex, destinationVertex) != null;
    }

    @Override
    public boolean containsVertex(Town v) {
        return townIndexMap.containsKey(v);
    }

    @Override
    public Set<Road> edgeSet() {
        return new HashSet<>(roads);
    }

    @Override
    public Set<Road> edgesOf(Town vertex) {
        if (!containsVertex(vertex)) throw new IllegalArgumentException();
        Set<Road> edges = new HashSet<>();
        for (Road road : roads) {
            if (road.contains(vertex)) {
                edges.add(road);
            }
        }
        return edges;
    }

    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road edgeToRemove = getEdge(sourceVertex, destinationVertex);
        if (edgeToRemove != null && edgeToRemove.getWeight() == weight && description.equals(edgeToRemove.getName())) {
            roads.remove(edgeToRemove);
            int sourceIndex = townIndexMap.get(sourceVertex);
            int destIndex = townIndexMap.get(destinationVertex);
            adjacencyMatrix[sourceIndex][destIndex] = 0;
            adjacencyMatrix[destIndex][sourceIndex] = 0;
            return edgeToRemove;
        }
        return null;
    }

    @Override
    public boolean removeVertex(Town v) {
        if (v == null || !containsVertex(v)) return false;
        int index = townIndexMap.get(v);
        towns.remove(v);
        townIndexMap.remove(v);
        roads.removeIf(road -> road.contains(v));

        // Update adjacency matrix
        int newSize = towns.size();
        int[][] newMatrix = new int[newSize][newSize];
        for (int i = 0, newI = 0; i < adjacencyMatrix.length; i++) {
            if (i == index) continue;
            for (int j = 0, newJ = 0; j < adjacencyMatrix.length; j++) {
                if (j == index) continue;
                newMatrix[newI][newJ++] = adjacencyMatrix[i][j];
            }
            newI++;
        }
        adjacencyMatrix = newMatrix;

        // Update town indices
        for (int i = 0; i < towns.size(); i++) {
            townIndexMap.put(towns.get(i), i);
        }

        return true;
    }

    @Override
    public Set<Town> vertexSet() {
        return new HashSet<>(towns);
    }

    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);

        ArrayList<String> path = new ArrayList<>();
        Town current = destinationVertex;

        // Reconstruct the path from destination to source
        while (current != null && !current.equals(sourceVertex)) {
            Town previous = previousVertexMap.get(current);
            if (previous == null) break;

            Road connectingRoad = getEdge(previous, current);
            if (connectingRoad == null) break;

            // Add the step in the required format
            path.add(0, previous.getName() + " via " + connectingRoad.getName() + " to " + current.getName() + " " + connectingRoad.getWeight() + " mi");
            current = previous;
        }

        return path.isEmpty() || !path.get(0).startsWith(sourceVertex.getName()) ? new ArrayList<>() : path;
    }



    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        previousVertexMap = new HashMap<>();
        distanceMap = new HashMap<>();
        PriorityQueue<Town> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distanceMap::get));

        // Initialize distances
        for (Town town : vertexSet()) {
            distanceMap.put(town, Integer.MAX_VALUE);
        }
        distanceMap.put(sourceVertex, 0);
        priorityQueue.add(sourceVertex);

        while (!priorityQueue.isEmpty()) {
            Town current = priorityQueue.poll();

            for (Road edge : edgesOf(current)) {
                Town neighbor = edge.getDestination().equals(current) ? edge.getSource() : edge.getDestination();
                int newDist = distanceMap.get(current) + edge.getWeight();

                if (newDist < distanceMap.get(neighbor)) {
                    distanceMap.put(neighbor, newDist);
                    previousVertexMap.put(neighbor, current);
                    priorityQueue.add(neighbor);
                }
            }
        }
    }

}
