import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class QueueBased<T> {
	private Queue<T> myQueue;
	int row;
	int col;
	String current;
	public QueueBased() {
		myQueue= new ArrayDeque<>();
	}
	public String getMaze(String[][][] maze) {
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
	
}

