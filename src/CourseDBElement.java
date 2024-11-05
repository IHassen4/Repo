public class CourseDBElement implements Comparable<CourseDBElement> {
    // Attributes
    private String courseID;
    private int crn;
    private int credits;
    private String roomNumber;
    private String instructorName;

    // Constructor
    public CourseDBElement(String courseID, int crn, int credits, String roomNumber, String instructorName) {
        this.courseID = courseID;
        this.crn = crn;
        this.credits = credits;
        this.roomNumber = roomNumber;
        this.instructorName = instructorName;
    }

    public CourseDBElement() {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
    public String getID() {
        return courseID;
    }

    public void setID(String courseID) {
        this.courseID = courseID;
    }

    public int getCRN() {
        return crn;
    }

    public void setCRN(int crn) {
        this.crn = crn;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    // Implementing the compareTo method from Comparable interface
    @Override
    public int compareTo(CourseDBElement other) {
        // Comparison based on CRN
        return Integer.compare(this.crn, other.crn);
    }

    @Override
    public String toString() {
        return String.format("\nCourse:%s CRN:%d Credits:%d Instructor:%s Room:%s", 
                             this.courseID, this.crn, this.credits, this.instructorName, this.roomNumber);
    }


    // Optional: Overriding equals and hashCode methods if needed
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDBElement that = (CourseDBElement) o;
        return crn == that.crn;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(crn);
    }
}

