import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class FileReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//call the StackBased class to get stack solution
		
		
		try {
		    String[][][] j = getTextCoords("HardMap1");
		    printMaze(j);
		    StackBased<String> sBased = new StackBased<>();
		    long startTime = System.nanoTime();
		    String[][][] newM = (fillMaze(j, sBased.getMaze(j)));
			
			long endTime = System.nanoTime();
			double elapsed = (endTime - startTime);
			System.out.println("Total Runtime: " + elapsed + " seconds");
			printMaze(newM);
			System.out.println("");
			//printCoordinate(n,queueBased.getMaze(n));
			//StackBased<String> sBased = new StackBased<>();

			

			
		} catch (IllegalMapCharacterException | IncompleteMapException | IncorrectMapFormatException e) {
		    System.out.println("Error: " + e.getMessage());
		    System.exit(-1);
		}
		
		
	}
	public static void printMaze(String[][][] newM) {
		for (int l = 0; l < newM.length; l++) {
		    for (int i = 0; i < newM[l].length; i++) {
		        for (int j = 0; j < newM[l][i].length; j++) {
		            System.out.print(newM[l][i][j]);
		        }
		        System.out.println("");
		    }
		    //System.out.println("");
		}
	}
	
	public static String[][][] getText(String passedFile) throws IllegalMapCharacterException, IncompleteMapException, IncorrectMapFormatException {{
		Queue<String> mapVals = new ArrayDeque<>();
		File file = new File(passedFile);
		//DOESNT WORK FULLY - NEEDS GTO CATCH EXTRA . AND @
		Scanner scan;
		try {
			scan = new Scanner(file);
			String rows = scan.next();
			String cols = scan.next();
			String levels = scan.next();
			try {
	            if (Integer.parseInt(rows) <= 0 || Integer.parseInt(cols) <= 0 || Integer.parseInt(levels) <= 0)
	                throw new IncorrectMapFormatException("Rows, cols, and levels must be positive non-zero numbers");
	        } catch (NumberFormatException e) {
	            throw new IncorrectMapFormatException("First line must be three positive non-zero numbers");
	        }
			int numRows = Integer.parseInt(rows);
	        int numCols = Integer.parseInt(cols);
	        int numLevels = Integer.parseInt(levels);
	        scan.nextLine();
			//DOESNT WORK FULLY - NEEDS GTO CATCH EXTRA . AND @

	        String[][][] coords = new String[numLevels][numRows][numCols];
	        for (int l = 0; l < numLevels; l++) {
	            for (int i = 0; i < numRows; i++) {
	            	if (!scan.hasNextLine()) {
	                    throw new IncompleteMapException("Not enough rows in map");
	            	}
	        		//DOESNT WORK FULLY - NEEDS GTO CATCH EXTRA . AND @

	                String line = scan.nextLine();
	                if (line.length() < numCols) {
	                    throw new IncompleteMapException("Row " + i + " has too few characters");
	                }
	                for (int j = 0; j < numCols; j++) {
	                	String ch = String.valueOf(line.charAt(j));
	                    if (!ch.matches("[.$W@|]"))
	                        throw new IllegalMapCharacterException("Illegal character found: " + ch);
	                    coords[l][i][j] = ch;
	                }
	            }
	        }

	        return coords;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}}
	
	public static String[][][] getTextCoords(String passedFile) throws IncorrectMapFormatException, IncompleteMapException, IncorrectMapFormatException, IllegalMapCharacterException {
		
		File file = new File(passedFile);
		
		Scanner scan;
		try {
			scan = new Scanner(file);
			String rows1 = scan.next();
			String cols1 = scan.next();
			String levels1 = scan.next();
			try {
			    if (Integer.parseInt(rows1) <= 0 || Integer.parseInt(cols1) <= 0 || Integer.parseInt(levels1) <= 0)
			        throw new IncorrectMapFormatException("Rows, cols, and levels must be positive non-zero numbers");
			} catch (NumberFormatException | IncorrectMapFormatException e) {
			    throw new IncorrectMapFormatException("First line must be three positive non-zero numbers");
			}
			String levels;
			String cols;
			String rows;
			String[][][] coords = new String[Integer.parseInt(levels1)][Integer.parseInt(rows1)][Integer.parseInt(cols1)];
			
			System.out.println(Integer.parseInt(rows1)*Integer.parseInt(levels1));
			
			while(scan.hasNext()) {
				String num = scan.next();
				if (!num.matches("[.$W@|]+")) {
				    throw new IllegalMapCharacterException("Illegal character found: " + num);
				}
				rows = scan.next();
				cols = scan.next();
				
				levels = scan.next();
				
				//String nextval = scan.next();
				
				if (Integer.parseInt(rows1) <= Integer.parseInt(rows) || Integer.parseInt(cols1) <= Integer.parseInt(cols) || Integer.parseInt(levels1) <= Integer.parseInt(levels)) {
				    throw new IncompleteMapException("Coordinates don't fit inside the maze: " + rows + "," + cols + "," + levels);
			}
				coords[Integer.parseInt(levels)][Integer.parseInt(rows)][Integer.parseInt(cols)] = num;
			}
			for (int l = 0; l < Integer.parseInt(levels1); l++)
	            for (int i = 0; i < Integer.parseInt(rows1); i++)
	                for (int j = 0; j < Integer.parseInt(cols1); j++)
	                    if (coords[l][i][j] == null)
	                        coords[l][i][j] = ".";

			return coords;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}
	

	
	
	public static String[][][] fillMaze(String[][][] maze, String path) {
		String[][][] newMaze = maze;
		String[] steps = path.split(" ");
		for(int i = 0; i<steps.length; i++) {
			String[] curr = steps[i].split(",");
			//System.out.println(curr[0]);
			if(!(newMaze[Integer.parseInt(curr[2])][Integer.parseInt(curr[0])][Integer.parseInt(curr[1])].equals("W")) && !newMaze[Integer.parseInt(curr[2])][Integer.parseInt(curr[0])][Integer.parseInt(curr[1])].equals("$")) {
				newMaze[Integer.parseInt(curr[2])][Integer.parseInt(curr[0])][Integer.parseInt(curr[1])] = "+";
			}
		}
		return newMaze;
      
        
	}
	
	public static void printCoordinate(String[][][] maze, String path) {
	    String[] steps = path.split(" ");
	    for (String step : steps) {
	        String[] parts = step.split(",");
	        int row = Integer.parseInt(parts[0]);
	        int col = Integer.parseInt(parts[1]);
	        int level = Integer.parseInt(parts[2]);
	        if (!maze[level][row][col].equals("W") && !maze[level][row][col].equals("$")) {
	            System.out.println("+ " + row + " " + col + " " + level);
	        }
	    }
	}

}
