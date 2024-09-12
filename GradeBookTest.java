import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {

	
	GradeBook gb1;
	GradeBook gb2;
	
	@BeforeEach
	void setUp() throws Exception {
		gb1 = new GradeBook(5);
		gb2 = new GradeBook(5);
		
		gb1.addScore(90);
		gb1.addScore(100);
		gb1.addScore(95);
		
		gb2.addScore(0);
		gb2.addScore(95);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		gb1 = null;
		gb2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(gb1.toString().equals("90.0 100.0 95.0"));
		assertTrue(gb2.toString().equals("0.0 95.0"));
		
		assertEquals(3, gb1.getScoreSize());
		assertEquals(2, gb2.getScoreSize());

	}
	
	@Test 
	void testSum() {
		assertEquals(285, gb1.sum());
		assertEquals(95, gb2.sum());
	}
	
	@Test 
	void testMinimum() {
		assertEquals(90, gb1.minimum());
		assertEquals(0, gb2.minimum());
	}
	
	@Test 
	void testFinalScore() {
		assertEquals(195, gb1.finalScore());
		assertEquals(95, gb2.finalScore());
	}

}
