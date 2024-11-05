
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CourseDBManager_STUDENT_Test {
    private CourseDBManager dbManager;

    @BeforeEach
    public void setUp() {
        dbManager = new CourseDBManager();
    }

    @Test
    public void testAddAndGetCourse() {
        // Add a course
        dbManager.add("CMSC204", 12345, 4, "SC-100", "Dr. Smith");
        
        // Retrieve the course
        CourseDBElement course = dbManager.get(12345);

        // Validate course details
        assertNotNull(course, "The course should not be null.");
        assertEquals("CMSC204", course.getID(), "Course ID should be CMSC204.");
        assertEquals(12345, course.getCRN(), "Course CRN should be 12345.");
        assertEquals(4, course.getCredits(), "Course credits should be 4.");
        assertEquals("SC-100", course.getRoomNumber(), "Room number should be SC-100.");
        assertEquals("Dr. Smith", course.getInstructorName(), "Instructor should be Dr. Smith.");
    }

    @Test
    public void testGetNonExistentCourse() {
        // Attempt to retrieve a course that hasn't been added
        CourseDBElement course = dbManager.get(99999);
        assertNull(course, "The course should be null as it doesn't exist.");
    }

    @Test
    public void testShowAllCourses() {
        // Add multiple courses
        dbManager.add("CMSC204", 12345, 4, "SC-100", "Dr. Smith");
        dbManager.add("ENGL101", 67890, 3, "ENG-201", "Prof. Brown");

        // Show all courses
        ArrayList<String> courses = dbManager.showAll();

        // Validate that both courses are returned in alphabetical order
        assertEquals(2, courses.size(), "There should be two courses in the list.");
        assertTrue(courses.get(0).contains("CMSC204"), "First course should be CMSC204.");
        assertTrue(courses.get(1).contains("ENGL101"), "Second course should be ENGL101.");
    }

    @Test
    public void testReadFile() throws FileNotFoundException {
        // Create a temporary file with course data
        File tempFile = new File("testCourses.txt");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.println("CMSC204 12345 4 SC-100 Dr. Smith");
        writer.println("ENGL101 67890 3 ENG-201 Prof. Brown");
        writer.close();

        // Read courses from file
        dbManager.readFile(tempFile);

        // Validate that the courses were added correctly
        CourseDBElement course1 = dbManager.get(12345);
        assertNotNull(course1, "Course 12345 should be found.");
        assertEquals("CMSC204", course1.getID(), "Course ID should be CMSC204.");

        CourseDBElement course2 = dbManager.get(67890);
        assertNotNull(course2, "Course 67890 should be found.");
        assertEquals("ENGL101", course2.getID(), "Course ID should be ENGL101.");

        // Delete temporary file after test
        tempFile.delete();
    }
}
