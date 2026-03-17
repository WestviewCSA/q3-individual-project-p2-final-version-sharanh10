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
		
		Queue q = getText("easyMap1");
		while(!q.isEmpty()) {
			System.out.println(q.poll());
		}
		System.out.println("");
		String[][][] n = getTextCoords("EasyMap2");
		
		//System.out.println(queueBased(n));
		//System.out.println(sBased(n));
		String[][][] newM = (fillMaze(n, queueBased(n)));
		for (int l = 0; l < newM.length; l++) {
		    for (int i = 0; i < newM[l].length; i++) {
		        for (int j = 0; j < newM[l][i].length; j++) {
		            System.out.print(newM[l][i][j]);
		        }
		        System.out.println("");
		    }
		    //System.out.println("");
		}
		System.out.println("");
		newM = (fillMaze(n, sBased(n)));
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
	
	public static Queue<String> getText(String passedFile) {
		Queue<String> mapVals = new ArrayDeque<>();
		File file = new File(passedFile);
		Scanner scan;
		try {
			scan = new Scanner(file);
			String rows = scan.next();
			String cols = scan.next();
			String levels = scan.next();
			if(Integer.parseInt(rows)<=0 || Integer.parseInt(cols) <= 0 || Integer.parseInt(levels) <= 0) {
				System.out.println("Not valid row column combinattion");
				Queue<String> mapVals1 = new ArrayDeque<>();
			}
			while(scan.hasNext()) {
				String nextVal = scan.next();
				
				
				if (!nextVal.matches("[.$W@]+")) {
				    // nextVal contains characters other than . $ W @
					System.out.println("Contains an invalid character");
					Queue<String> mapVals1 = new ArrayDeque<>();
					return mapVals1;

				}
				else {
					mapVals.add(nextVal);
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapVals;

	}
	
	public static String[][][] getTextCoords(String passedFile) {
		File file = new File(passedFile);
		
		Scanner scan;
		try {
			scan = new Scanner(file);
			String rows1 = scan.next();
			String cols1 = scan.next();
			String levels1 = scan.next();
			String levels;
			String cols;
			String rows;
			String[][][] coords = new String[Integer.parseInt(levels1)][Integer.parseInt(rows1)][Integer.parseInt(cols1)];
			
			System.out.println(Integer.parseInt(rows1)*Integer.parseInt(levels1));
			
			while(scan.hasNext()) {
				String num = scan.next();
				if (!num.matches("[.$W@|]+")) {
				    // nextVal contains characters other than . $ W @
					System.out.println("Contains an invalid character");
					String[][][] coords1 = new String[0][0][0];
					return coords1;

				}
				rows = scan.next();
				cols = scan.next();
				
				levels = scan.next();
				
				//String nextval = scan.next();
				
				if(Integer.parseInt(rows1) <= Integer.parseInt(rows) || Integer.parseInt(cols1)<=Integer.parseInt(cols)) {
					System.out.println("Map specs isn't accurate");
					String[][][] coords1 = new String[0][0][0];
					return coords1;

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
	
	public static String queueBased(String[][][] maze) {
		Queue<String> mapVals = new ArrayDeque<>();
		Queue<String> visited = new ArrayDeque<>();
		int row = 0, col = 0;
	    for (int r = 0; r < maze[0].length; r++)
	        for (int c = 0; c < maze[0][0].length; c++)
	            if (maze[0][r][c].equals("W")) { 
	            	row = r; col = c; 
	            	
	            }

		//assume that starting position is at 1,0 for now - we can fix later
		String current = row + "," + col+","+0;
		mapVals.add(current);
		visited.add(current);
		while(!mapVals.isEmpty()) {	
			//System.out.println(current);
			current = mapVals.poll(); 
			String[] steps = current.split(" ");
	        String lastStep = steps[steps.length - 1];
	        String[] parts = lastStep.split(",");
	        row = Integer.parseInt(parts[0]);
	        col = Integer.parseInt(parts[1]);
	        int level = Integer.parseInt(parts[2]);

			visited.add(current);
			//if not already added then add it
			
			
			if(!(row-1 < 0)&& !visited.contains((row-1)+","+col+","+level) && !maze[level][row-1][col].equals("@")) {
				
				String newOption = (row-1) + "," + col+","+level;
				String newPath  = current + " " + newOption;
				if(maze[level][row-1][col].equals("$")) {
					//System.out.println(newPath);
					return newPath;
				}
				if (maze[level][row-1][col].equals("|")) {
					newOption = moveLevels(maze, level + 1);
	                newPath = current + " " + newOption;
	            }
				visited.add(newOption);
				mapVals.add(newPath);
			}
			if(!(row+1 > maze[0].length-1)&& !visited.contains((row+1)+","+col+","+level) && !maze[level][row+1][col].equals("@")) {
				String newOption = (row+1) + "," + col+","+level;
				String newPath  = current + " " + newOption;
				if(maze[level][row+1][col].equals("$")) {
					//System.out.println(newPath);
					return newPath;
				}
				if (maze[level][row+1][col].equals("|")) {
					newOption = moveLevels(maze, level + 1);
	                newPath = current + " " + newOption;
	            }
				visited.add(newOption);
				mapVals.add(newPath);
			}
			//check if any of them is the $ sign
			
			if(!(col+1 > maze[0][0].length-1)&& !visited.contains((row)+","+(col+1)+","+level) && !maze[level][row][col+1].equals("@")) {
				String newOption =(row) + "," + (col+1)+","+level;
				String newPath  = current + " " + newOption;
				if(maze[level][row][col+1].equals("$")) {
					//System.out.println(newPath);
					return newPath;
				}
				if (maze[level][row][col+1].equals("|")) {
					newOption = moveLevels(maze, level + 1);
	                newPath = current + " " + newOption;
	            }
				visited.add(newOption);
				mapVals.add(newPath);
			}
			if(!(col-1 < 0)&& !visited.contains((row)+","+(col-1)+","+level) && !maze[level][row][col-1].equals("@")) {
				String newOption = (row) + "," + (col-1)+","+level;
				String newPath  = current + " " + newOption;
				if(maze[level][row][col-1].equals("$")) {
					//System.out.println(newPath);
					return newPath;
				}
				if (maze[level][row][col-1].equals("|")) {
					newOption = moveLevels(maze, level + 1);
	                newPath = current + " " + newOption;
	            }
				visited.add(newOption);
				mapVals.add(newPath);
			}
	
		}
		//if level found teleport
		return "";
	}
	//stac based
	private static String moveLevels(String[][][] maze, int nextLevel) {
	    for (int r = 0; r < maze[nextLevel].length; r++)
	        for (int c = 0; c < maze[nextLevel][0].length; c++)
	            if (maze[nextLevel][r][c].equals("W"))
	                return r + "," + c + "," + nextLevel;
	    return null;
	}
	
	public static String sBased(String[][][] maze) {
		Stack<String> mapVals = new Stack<>();
		Stack<String> visited = new Stack<>();
		int row = 0, col = 0;
	    for (int r = 0; r < maze[0].length; r++)
	        for (int c = 0; c < maze[0][0].length; c++)
	            if (maze[0][r][c].equals("W")) { 
	            	row = r; col = c; 
	            	
	            }

		//assume that starting position is at 1,0 for now - we can fix later
		String current = row + "," + col+","+0;
		mapVals.push(current);
		visited.push(current);
		while(!mapVals.isEmpty()) {	
			//System.out.println(current);
			current = mapVals.pop(); 
			String[] steps = current.split(" ");
	        String lastStep = steps[steps.length - 1];
	        String[] parts = lastStep.split(",");
	        row = Integer.parseInt(parts[0]);
	        col = Integer.parseInt(parts[1]);
	        int level = Integer.parseInt(parts[2]);
			visited.push(current);
			//if not already added then add it
			
			
			if(!(row-1 < 0)&& !visited.contains((row-1)+","+col+","+level) && !maze[level][row-1][col].equals("@")) {
				
				String newOption = (row-1) + "," + col+","+level;
				String newPath  = current + " " + newOption;
				if(maze[level][row-1][col].equals("$")) {
					//System.out.println(newPath);
					return newPath;
				}
				if (maze[level][row-1][col].equals("|")) {
					newOption = moveLevels(maze, level + 1);
	                newPath = current + " " + newOption;
	            }
				visited.push(newOption);
				mapVals.push(newPath);
			}
			if(!(row+1 > maze[0].length-1)&& !visited.contains((row+1)+","+col+","+level) && !maze[level][row+1][col].equals("@")) {
				String newOption = (row+1) + "," + col+","+level;
				String newPath  = current + " " + newOption;
				if(maze[level][row+1][col].equals("$")) {
					//System.out.println(newPath);
					return newPath;
				}
				if (maze[level][row+1][col].equals("|")) {
					newOption = moveLevels(maze, level + 1);
	                newPath = current + " " + newOption;
	            }
				visited.push(newOption);
				mapVals.push(newPath);
			}
			//check if any of them is the $ sign
			
			if(!(col+1 > maze[0][0].length-1)&& !visited.contains((row)+","+(col+1)+","+level) && !maze[level][row][col+1].equals("@")) {
				String newOption =(row) + "," + (col+1)+","+level;
				String newPath  = current + " " + newOption;
				if(maze[level][row][col+1].equals("$")) {
					//System.out.println(newPath);
					return newPath;
				}
				if (maze[level][row][col+1].equals("|")) {
					newOption = moveLevels(maze, level + 1);
	                newPath = current + " " + newOption;
	            }
				visited.push(newOption);
				mapVals.push(newPath);
			}
			if(!(col-1 < 0)&& !visited.contains((row)+","+(col-1)+","+level) && !maze[level][row][col-1].equals("@")) {
				String newOption = (row) + "," + (col-1)+","+level;
				String newPath  = current + " " + newOption;
				if(maze[level][row][col-1].equals("$")) {
					//System.out.println(newPath);
					return newPath;
				}
				if (maze[level][row][col-1].equals("|")) {
					newOption = moveLevels(maze, level + 1);
	                newPath = current + " " + newOption;
	            }
				visited.push(newOption);
				mapVals.push(newPath);
			}
	
		}
		//if level found teleport
		return "";
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

}

