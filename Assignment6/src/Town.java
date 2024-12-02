
public class Town extends Object implements Comparable<Town> {

	private String name;

	public Town(String name) {
        this.name = name;
    }
	
	public Town(Town templateTown) {
        this.name = templateTown.name;
    }
	
	public String getName() {
        return name;
    }
	
	@Override
    public int compareTo(Town o) {
        return this.name.compareTo(o.name);
    }
	
	 @Override
	    public String toString() {
	        return name;
	    }
	 @Override
	    public int hashCode() {
	        return name.hashCode();
	    }
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true; // Check if comparing with itself
	        }
	        if (obj == null || getClass() != obj.getClass()) {
	            return false; // Check if obj is null or a different class
	        }
	        Town otherTown = (Town) obj;
	        return name.equals(otherTown.name); // Compare town names
	    }
}
