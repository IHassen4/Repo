
public class Road extends Object implements Comparable<Road>{
	
	 private Town source;
	    private Town destination;
	    private int degrees; // Weight of the edge
	    private String roadName;

	    /**
	     * Constructor for the Road class.
	     * @param source - One town on the road
	     * @param destination - Another town on the road
	     * @param degrees - Weight of the edge, i.e., distance from one town to the other
	     * @param name - Name of the road
	     */
	    public Road(Town source, Town destination, int degrees, String name) {
	        this.source = source;
	        this.destination = destination;
	        this.degrees = degrees;
	        this.roadName = name;
	    }
	    
	    public Road(Town source, Town destination, String name) {
	        this.source = source;
	        this.destination = destination;
	        this.degrees = 1; // Preset weight
	        this.roadName = name;
	    }
	    
	    public boolean contains(Town town) {
	        return source.equals(town) || destination.equals(town);
	    }

	    // Override the toString method
	    @Override
	    public String toString() {
	        return roadName + " connects " + source + " and " + destination + " with a weight of " + degrees;
	    }

	    // Method to return the name of the road
	    public String getName() {
	        return roadName;
	    }
	    public Town getDestination() {
	        return destination;
	    }

	    /**
	     * Returns the first town on the road.
	     * @return The source town.
	     */
	    public Town getSource() {
	        return source;
	    }

	    /**
	     * Compares this road with another road based on the road name.
	     * @param o - The other road to compare to.
	     * @return 0 if the road names are the same,
	     *         a positive or negative number if the road names are different.
	     */
	    @Override
	    public int compareTo(Road o) {
	        return this.roadName.compareTo(o.roadName);
	    }

	    public int getWeight() {
	        return degrees;
	    }

	    /**
	     * Compares this road to another object to check if they are equal.
	     * @param obj - The object to compare this road to.
	     * @return true if the roads connect the same towns (in any order), false otherwise.
	     */
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null || getClass() != obj.getClass()) {
	            return false;
	        }
	        Road otherRoad = (Road) obj;

	        // Check if the source and destination towns match (in any order)
	        return (this.source.equals(otherRoad.source) && this.destination.equals(otherRoad.destination))
	            || (this.source.equals(otherRoad.destination) && this.destination.equals(otherRoad.source));
	    }
}
