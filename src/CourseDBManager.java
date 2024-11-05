import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CourseDBManager implements CourseDBManagerInterface {
    private CourseDBStructure CDS;

    // No-argument constructor for testing purposes
    public CourseDBManager() {
        CDS = new CourseDBStructure(10); // Default estimated size for testing
    }

    // Constructor with estimated course size
    public CourseDBManager(int estimatedCourses) {
        CDS = new CourseDBStructure(estimatedCourses);
    }

    // Adds a course to the CourseDBStructure
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
        CDS.add(element);
    }

    // Retrieves a CourseDBElement by CRN
    @Override
    public CourseDBElement get(int crn) {
        try {
            return CDS.get(crn);
        } catch (IOException e) {
            System.out.println("Course with CRN " + crn + " not found.");
            return null;
        }
    }

    // Reads courses from a file and adds them to the CourseDBStructure
    @Override
    public void readFile(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");

            if (data.length >= 5) { // Ensure all fields are present
                try {
                    String courseID = data[0];
                    int crn = Integer.parseInt(data[1]);
                    int credits = Integer.parseInt(data[2]);
                    String roomNumber = data[3];
                    String instructorName = data[4];

                    add(courseID, crn, credits, roomNumber, instructorName);
                } catch (NumberFormatException e) {
                    System.out.println("Error reading data: " + e.getMessage());
                }
            }
        }

        scanner.close();
    }

    // Returns a list of all courses in the CourseDBStructure as strings
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> courses = CDS.showAll();
        courses.sort((a, b) -> a.compareToIgnoreCase(b)); // Sorts alphabetically and case-insensitively
        return courses;
    }


}
