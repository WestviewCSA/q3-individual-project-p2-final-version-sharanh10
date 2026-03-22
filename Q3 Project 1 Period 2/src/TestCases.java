import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class TestCases {

	@Test
	void testNoSolution() throws Exception {
        String[][][] maze = p1.getText("easyMap4");
        QueueBased<String> q = new QueueBased<>();
        String path = q.getMaze(maze);
        assertEquals("The Wolverine Store is closed.", path);
    }
	
	@Test
    void testOptimalShorterThanStack() throws Exception {
        String[][][] maze = p1.getText("easyMap3");
        StackBased<String> s = new StackBased<>();
        Optimal<String> o = new Optimal<>();
        String stackPath = s.getMaze(maze);
        String optPath = o.getMaze(maze);

        int stackSteps = stackPath.split(" ").length;
        int optSteps = optPath.split(" ").length;

        assertTrue("Optimal should be <= stack steps", optSteps <= stackSteps);
    }

	@Test
	public void testIllegalCharacter() {
	    try {
	        p1.getText("illegalMap1");
	        fail("Expected IllegalMapCharacterException to show");
	    } catch (IllegalMapCharacterException e) {
	        assertEquals("Illegal character found: X", e.getMessage());
	    } catch (Exception e) {
	        //
	    }
	}
	@Test
	void testWrongChar() {
	    try {
	        p1.getText("wrongMapFormat");
	        fail("Expected IncompleteMapException to show");
	    } catch (IncompleteMapException e) {
	        assertEquals("Row 0 has too many characters", e.getMessage());
	    } catch (Exception e) {
	        //
	    }
	}
	
	@Test
	void optimalShorterThanStack() {
	    
		try {
			String[][][] str = p1.getText("easyMap1");
			StackBased<String> s = new StackBased<>();
	        Optimal<String> o = new Optimal<>();
	        String stackPath = s.getMaze(str);
	        String optPath = o.getMaze(str);
	        assertTrue(stackPath.length()>=optPath.length());
		} catch (IllegalMapCharacterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncompleteMapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectMapFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	    
	}
	@Test
	void testQueueMaze() {
	    
		try {
			String[][][] maze = p1.getTextCoords("easyMapCoords");
			QueueBased<String> q = new QueueBased<>();
	        String qPath = q.getMaze(maze);
	        String str = 
	        	    "+ 3 8 0\n" +
	        	    "+ 3 9 0\n" +
	        	    "+ 4 9 0\n" +
	        	    "+ 5 9 0\n" +
	        	    "+ 5 8 0\n" +
	        	    "+ 5 7 0\n" +
	        	    "+ 6 7 0\n" +
	        	    "+ 7 7 0";
	        assertTrue(p1.printCoordinate(maze, qPath).equals(str));
		} catch (IllegalMapCharacterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncompleteMapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectMapFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	    
	}
	
	@Test
	void testStack() {
	    
		try {
			String[][][] maze = p1.getText("easyMap2");
			StackBased<String> s = new StackBased<>();
	        String sPath = s.getMaze(maze);
	        String str =
	        	    "@W@@@\n" +
	        	    "@++@.\n" +
	        	    "@@+@@\n" +
	        	    "@@$@.";
	        assertTrue(p1.printMaze(p1.fillMaze(maze, sPath)).equals(str));
		} catch (IllegalMapCharacterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncompleteMapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectMapFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	    
	}
	@Test
	void incorrectMapException() {
	    try {
	        p1.getText("illegalMap1");
	        fail("Expected Incorrect Format to show");
	    } catch (IncorrectMapFormatException e) {
	        assertEquals("Rows, cols, and levels must be positive non-zero numbers", e.getMessage());
	    } catch (Exception e) {
	        //
	    }
	}
	
	@Test
	void checkRunTime() throws Exception{
	    
	    	long startTime = System.nanoTime();
	    	String[][][] maze;
			
				maze = p1.getTextCoords("HardMap1");
				StackBased<String> s = new StackBased<>();
		        String sPath = s.getMaze(maze);	    
				long endTime = System.nanoTime();
				double elapsed = (endTime - startTime)/(1000000000.0);
		        assertTrue(elapsed < 9);
			
			
	    
	    
	}
	
	@Test
	void checkLastVisitedCoord() throws Exception{
	    
	    	long startTime = System.nanoTime();
	    	String[][][] maze;
			
	    	maze = p1.getText("HardMap2");
	    	Optimal<String> opt = new Optimal<>();
		    String oPath = opt.getMaze(maze);	 
		    String[] lastVis = oPath.split(" ");
		    String lastVisVal = "" + lastVis[lastVis.length-2].substring(0,1) + "," + lastVis[lastVis.length-2].substring(2,3)+ "," + lastVis[lastVis.length-2].substring(4,5);
		    assertTrue(lastVisVal.equals("7,2,2"));
			
			
	    
	    
	}
	
}
